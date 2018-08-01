package com.netease.timemachine.account.controller;

import com.alibaba.fastjson.JSONObject;
import com.netease.timemachine.account.dto.ChildDTO;
import com.netease.timemachine.account.dto.UserDTO;
import com.netease.timemachine.account.service.GroupService;
import com.netease.timemachine.account.service.MsService;
import com.netease.timemachine.account.service.UserService;
import com.netease.timemachine.account.util.ChildBirthDay;
import com.netease.timemachine.account.util.ChildVoToDtoUtil;
import com.netease.timemachine.account.util.ResponseView;
import com.netease.timemachine.account.util.UserVoToDtoUtil;
import com.netease.timemachine.account.vo.ChildVO;
import com.netease.timemachine.account.vo.UserVO;
import com.netease.timemachine.auth.meta.RsaAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 15:01 2018/7/17
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MsService msService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private RsaAlgorithm rsaAlgorithm;

    @RequestMapping(value = "/sms",method = RequestMethod.POST)
    public ResponseEntity smsByPhone(@RequestParam String phone){
        try{
            JSONObject jsonObject = msService.sms(phone);
            if(jsonObject.getInteger("code") != 200){
                return ResponseView.fail(100, "发送失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseView.fail(500, "服务器内部错误");
        }
        return ResponseView.success(null,"发送成功");
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity login(@RequestParam String phone, @RequestParam String code){
        try {
            JSONObject res = msService.sms(phone);
            if(res.getInteger("code") != 200){
                return ResponseView.fail(100, "发送失败");
            }
        }catch (Exception e) {
            return ResponseView.fail(500, "验证失败");
        }
        UserDTO userDTO = userService.selectByPhone(phone);
        JSONObject jsonObject = new JSONObject();
        if(userDTO == null){
            userDTO = new UserDTO();
            userDTO.setPhone(phone);
            userService.insertUser(userDTO);
            userDTO = userService.selectByPhone(phone);
        }else{
            List<ChildDTO> childDTOList = userService.selectOwnChildren(userDTO.getUserId());
            if(!CollectionUtils.isEmpty(childDTOList)){
                jsonObject.put("child", ChildVoToDtoUtil.childDtoToVo(childDTOList.get(0)));
            }
        }
        UserVO userVo = UserVoToDtoUtil.UserDtoToVo(userDTO);
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userVo.getUserId());
        map.put("phone", phone);
        String token = rsaAlgorithm.create(null, map);
        JSONObject json= new JSONObject();
        json.put("user", userVo);
        json.put("token", token);
        return ResponseView.success(json,"登录成功");
    }

    /**
     * 更新用户时，记得用户的图片要同步更新到group表里面
     * @param userVO
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity updateUser(@RequestBody UserVO userVO){
        UserDTO userDTO = UserVoToDtoUtil.UserVoToDto(userVO);
        userService.updateUser(userDTO);
        groupService.updateGroupImg(userVO.getImgUrl(), userVO.getUserId());
        return ResponseView.success("", "更新成功");
    }

    @RequestMapping(value = "/detail")
    public ResponseEntity selectUserById(@RequestParam Long userId){
        UserDTO userDTO = userService.selectById(userId);
        UserVO userVO = UserVoToDtoUtil.UserDtoToVo(userDTO);
        return ResponseView.success(userVO);
    }


    @RequestMapping("/ownChildren")
    public ResponseEntity showChildren(@RequestParam Long userId){
        List<ChildDTO> childDTOList = userService.selectOwnChildren(userId);
        if(!CollectionUtils.isEmpty(childDTOList)) {
            List<ChildVO> childVOList = ChildVoToDtoUtil.childDtoToVoList(childDTOList);
            List<ChildVO> res = new ArrayList<>();
            for(ChildVO childVO : childVOList){
                childVO.setAge(ChildBirthDay.getAge(childVO.getBirthDate()));
                res.add(childVO);
            }
            return ResponseView.success(res);
        }
        return ResponseView.success(null, "您还没有添加过孩子哦");
    }
}
