package com.netease.timemachine.account.controller;

import com.alibaba.fastjson.JSONObject;
import com.netease.timemachine.account.dto.ChildDTO;
import com.netease.timemachine.account.dto.UserDTO;
import com.netease.timemachine.account.service.GroupService;
import com.netease.timemachine.account.service.MsService;
import com.netease.timemachine.account.service.UserService;
import com.netease.timemachine.account.util.ChildVoToDtoUtil;
import com.netease.timemachine.account.util.ResponseView;
import com.netease.timemachine.account.util.UserVoToDtoUtil;
import com.netease.timemachine.account.vo.ChildVO;
import com.netease.timemachine.account.vo.UserVO;
import com.netease.timemachine.auth.annotation.JWTVerify;
import com.netease.timemachine.auth.meta.RsaAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("/sms")
    public ResponseEntity smsByPhone(@RequestParam String phone){
        try{
            msService.sms(phone);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseView.fail(500, "服务器内部错误");
        }
        return ResponseView.success(null,"发送成功");
    }

    @RequestMapping("/login")
    public ResponseEntity login(@RequestParam String phone, @RequestParam String code){
        try {
            msService.vms(phone, code);
        }catch (Exception e){
            return ResponseView.fail(500, "服务器内部错误");
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
        map.put("userId", userVo.getUserId());
        map.put("phone", phone);
        String token = rsaAlgorithm.create(null, map);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", userVo);
        jsonObject.put("token", token);
        return ResponseView.success(jsonObject,"登录成功");
    }

    @JWTVerify
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity updateUser(@RequestBody UserVO userVO, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        rsaAlgorithm.verify(token).getPlayloadMap();
        UserDTO userDTO = UserVoToDtoUtil.UserVoToDto(userVO);
        userService.updateUser(userDTO);
        groupService.updateGroupImg(userVO.getImgUrl(), userVO.getUserId());
        return ResponseView.success("", "更新成功");
    }

    @RequestMapping("/ownChildren")
    public ResponseEntity showChildren(@RequestParam Long userId){
        List<ChildDTO> childDTOList = userService.selectOwnChildren(userId);
        if(!CollectionUtils.isEmpty(childDTOList)) {
            List<ChildVO> childVOList = ChildVoToDtoUtil.childDtoToVoList(childDTOList);
            return ResponseView.success(childVOList);
        }
        return ResponseView.success(null, "您还没有添加过孩子哦");
    }

}
