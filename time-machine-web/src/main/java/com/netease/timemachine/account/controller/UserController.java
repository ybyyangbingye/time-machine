package com.netease.timemachine.account.controller;

import com.alibaba.fastjson.JSONObject;
import com.netease.timemachine.account.dto.ChildDTO;
import com.netease.timemachine.account.dto.UserDTO;
import com.netease.timemachine.account.service.GroupService;
import com.netease.timemachine.account.service.MsService;
import com.netease.timemachine.account.service.UserService;
import com.netease.timemachine.account.util.*;
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
        String res = "";
        try{
            res = msService.sms(phone);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseView.fail(500, "服务器内部错误");
        }
        JSONObject jsonObject = JSONObject.parseObject(res);
        if(jsonObject.getInteger("code") != 200){
            return ResponseView.fail(100, "发送失败");
        }
        return ResponseView.success(null,"发送成功");
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity login(@RequestParam String phone, @RequestParam String code){
        String res = "";
        try {
            res = msService.vms(phone, code);
        }catch (Exception e){
            return ResponseView.fail(500, "验证失败");
        }
        System.out.println(res);
        JSONObject jsonObject = JSONObject.parseObject(res);
        if(jsonObject.getInteger("code") != 200){
            return ResponseView.fail(500, "验证失败");
        }
        UserDTO userDTO = userService.selectByPhone(phone);
        if(userDTO == null){
            userDTO = new UserDTO();
            userDTO.setPhone(phone);
            userService.insertUser(userDTO);
            userDTO = userService.selectByPhone(phone);
        }
        UserVO userVo = UserVoToDtoUtil.UserDtoToVo(userDTO);
        Map<String, Object> map = new HashMap<>(2);
        map.put("user", userVo);
        map.put("phone", phone);
        String token = rsaAlgorithm.create(null, map);
        JSONObject json= new JSONObject();
        json.put("userId", userVo.getUserId());
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
