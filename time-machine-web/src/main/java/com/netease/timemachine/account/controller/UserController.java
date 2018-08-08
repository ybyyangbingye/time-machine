package com.netease.timemachine.account.controller;

import com.alibaba.fastjson.JSONObject;
import com.netease.timemachine.account.dto.ChildDTO;
import com.netease.timemachine.account.dto.GroupDTO;
import com.netease.timemachine.account.dto.UserDTO;
import com.netease.timemachine.account.service.ChildService;
import com.netease.timemachine.account.service.GroupService;
import com.netease.timemachine.account.service.MsService;
import com.netease.timemachine.account.service.UserService;
import com.netease.timemachine.account.util.*;
import com.netease.timemachine.account.vo.ChildVO;
import com.netease.timemachine.account.vo.UserVO;
import com.netease.timemachine.auth.meta.RsaAlgorithm;
import com.netease.timemachine.common.dto.MessageDTO;
import com.netease.timemachine.common.service.MessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.netease.timemachine.account.enums.AccountEnum.*;

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

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChildService childService;

    /**
     * 返回验证码页面
     * @return
     */
    @GetMapping(value="/generatePicCode")
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    public String validateCode(HttpServletRequest request, HttpServletResponse response) {
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        HttpSession session = request.getSession();

        ValidateCode vCode = new ValidateCode(120,40,5,100);
        session.setAttribute("code", vCode.getCode());
        try{
            vCode.write(response.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
            return "获取图形验证码失败";
        }
        return "成功获取图形验证码";
    }

    @PostMapping("/verifyPicCode")
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    public ResponseEntity verifyPictureCode(@RequestParam() String code,
                                            HttpServletRequest request){
        HttpSession session = request.getSession();
        String sessionCode = (String) session.getAttribute("code");
        if (!StringUtils.equalsIgnoreCase(code, sessionCode)) {
            return ResponseView.fail(PICCODE_FAILED.getCode(), PICCODE_FAILED.getMessage());
        }
        return ResponseView.success(null, "图形验证码验证成功");
    }

    @RequestMapping(value = "/sms",method = RequestMethod.POST)
    public ResponseEntity smsByPhone(@RequestParam String phone){
        boolean res = false;
        try{
            res  = msService.sms(phone);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseView.fail(INNER_ERROR.getCode(), INNER_ERROR.getMessage());
        }
        if(res){
            return ResponseView.success(null,"发送成功");
        }else {
            return ResponseView.fail(SEND_FAILED.getCode(), SEND_FAILED.getMessage());
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity login(@RequestParam String phone, @RequestParam String code){
        boolean res = false;
        try {
            res = msService.vms(phone, code);
        }catch (Exception e) {
            return ResponseView.fail(INNER_ERROR.getCode(), INNER_ERROR.getMessage());
        }
        if(!res) {
            return ResponseView.fail(VERIFY_FAILED.getCode(), VERIFY_FAILED.getMessage());
        }
        UserDTO userDTO = userService.selectByPhone(phone);
        JSONObject jsonObject = new JSONObject();
        if(userDTO == null){
            userDTO = new UserDTO();
            userDTO.setPhone(phone);
            userService.insertUser(userDTO);
            userDTO = userService.selectByPhone(phone);
            userDTO.setIsRegistered(false);
            jsonObject.put("child", null);
        }else{
            List<ChildDTO> childDTOList = userService.selectOwnChildren(userDTO.getUserId());
            if(!CollectionUtils.isEmpty(childDTOList)){
                ChildVO childVO = ChildVoToDtoUtil.childDtoToVo(childDTOList.get(0));
                childVO.setAge(ChildBirthDay.getAge(childVO.getBirthDate()));
                jsonObject.put("child", childVO);
            }
            userDTO.setIsRegistered(true);
        }
        UserVO userVo = UserVoToDtoUtil.UserDtoToVo(userDTO);
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userVo.getUserId());
        map.put("phone", phone);
        String token = rsaAlgorithm.create(null, map);
        jsonObject.put("user", userVo);
        jsonObject.put("token", token);
        return ResponseView.success(jsonObject,"登录成功");
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

    /**
     * 引导页+app内部申请关联孩子,直接绑定
     * @param userId
     * @param childId
     * @return
     */
    @PostMapping("/apply")
    public ResponseEntity managerChildByCode(@RequestParam Long userId,
                                             @RequestParam Long childId) {
        GroupDTO groupDTO = groupService.selectByUserAndChildId(userId, childId);
        if(groupDTO != null){
            return ResponseView.fail(BINED_REPEAT.getCode(), BINED_REPEAT.getMessage());
        }
        ChildDTO childDTO = childService.selectChildById(childId);
        UserDTO userDTO = userService.selectById(userId);
        groupDTO = new GroupDTO(childId, userId, "其他", "其他", 2, userDTO.getImgUrl());
        groupService.insertGroup(groupDTO);
        return ResponseView.success(ChildVoToDtoUtil.childDtoToVo(childDTO), "绑定该宝宝成功");
    }

    /**
     * 引导页+app内部申请关联孩子(后续工作)
     * @param userId
     * @param childId
     * @return
     */
    @PostMapping("/applyTodo")
    public ResponseEntity managerChildByCodeNext(@RequestParam Long userId,
                                             @RequestParam Long childId) {
        GroupDTO groupDTO = groupService.selectByUserAndChildId(userId, childId);
        if(groupDTO != null){
            return ResponseView.fail(BINED_REPEAT.getCode(), BINED_REPEAT.getMessage());
        }
        Long receiverId = groupService.selectChildCreator(childId);
        UserDTO userDTO = userService.selectById(userId);
        if(messageService.isExistMessage(userId, receiverId, childId, 4)){
            return ResponseView.fail(APPLY_REPEAT.getCode(), APPLY_REPEAT.getMessage());
        }
        ChildDTO childDTO = childService.selectChildById(childId);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setSenderId(userId);
        messageDTO.setReceiverId(receiverId);
        messageDTO.setGroupType(4);
        messageDTO.setGroupId(childId);
        messageDTO.setContent(userDTO.getUserName() + "申请关联您的宝宝" + childDTO.getChildName());
        messageService.addMessage(messageDTO);
        return ResponseView.success(null,"申请关联宝宝成功");
    }

    /**
     * 微信点击链接主动关联孩子,直接绑定
     * @param invitationCode
     * @param phone
     * @return
     */
    @PostMapping("/association")
    public ResponseEntity managerChildByCode(@RequestParam String invitationCode,
                                             @RequestParam String phone) {
        Long childId = ChildInvitationCode.inviDecoding(invitationCode);
        if(childId!=null && childService.selectChildById(childId)!=null) {
            UserDTO userDTO = userService.selectByPhone(phone);
            if (userDTO == null) {
                return ResponseView.fail(USER_NULL.getCode(), USER_NULL.getMessage());
            }
            Long userId = userDTO.getUserId();
            GroupDTO groupDTO = groupService.selectByUserAndChildId(userId, childId);
            if (groupDTO != null) {
                return ResponseView.fail(BINED_REPEAT.getCode(), BINED_REPEAT.getMessage());
            }
            groupDTO = new GroupDTO(childId, userId, "其他", "其他", 2, userDTO.getImgUrl());
            groupService.insertGroup(groupDTO);
            return ResponseView.success(null, "绑定该宝宝成功");
        }
        return ResponseView.fail(BIND_FAILED.getCode(), BIND_FAILED.getMessage());
    }
}
