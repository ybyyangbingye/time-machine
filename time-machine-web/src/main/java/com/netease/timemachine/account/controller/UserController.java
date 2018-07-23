package com.netease.timemachine.account.controller;

import com.netease.timemachine.account.dto.ChildDTO;
import com.netease.timemachine.account.dto.UserDTO;
import com.netease.timemachine.account.service.UserService;
import com.netease.timemachine.account.util.ChildVoToDtoUtil;
import com.netease.timemachine.account.util.FileUtil;
import com.netease.timemachine.account.util.ResponseView;
import com.netease.timemachine.account.util.UserVoToDtoUtil;
import com.netease.timemachine.account.vo.ChildVO;
import com.netease.timemachine.account.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    /**
     * 用户登录
     * 根据phone查询用户是否已存在，存在则添加
     * @param phone
     * @return 返回含有userId的userVO对象
     */
    @RequestMapping("/login")
    public ResponseEntity loginByPhone(@RequestParam String phone){
        UserDTO userDTO = userService.selectByPhone(phone);
        if(userDTO == null){
            userDTO = new UserDTO();
            userDTO.setPhone(phone);
            userService.insertUser(userDTO);
            userDTO = userService.selectByPhone(phone);
        }
        return ResponseView.success(UserVoToDtoUtil.UserDtoToVo(userDTO));
    }

//    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity insertUser(@RequestBody UserVO userVO){
//        userService.insertUser(UserVoToDtoUtil.UserVoToDto(userVO));
//        return ResponseView.success("", "添加成功");
//    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseEntity insertUser(@RequestParam(required = false) String userName,
                                     @RequestParam(required = false) String phone,
                                     @RequestParam(required = false) Integer identification,
                                     @RequestParam(required = false) String address,
                                     @RequestParam(required = false) MultipartFile imgUrl,
                                     HttpServletRequest request){
        //userService.insertUser(UserVoToDtoUtil.UserVoToDto(userVO));
        String contentType = imgUrl.getContentType();
        String fileName = imgUrl.getOriginalFilename();
        System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);
        System.out.println("size-->" + imgUrl.getSize());
        String filePath = "D://imgupload/";
        try {
            FileUtil.uploadFile(imgUrl.getBytes(), filePath, fileName);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return ResponseView.success("", "添加成功");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity updateUser(@RequestBody UserVO userVO){
        UserDTO userDTO = UserVoToDtoUtil.UserVoToDto(userVO);
        userService.updateUser(userDTO);
        return ResponseView.success("", "更新成功");
    }

    @RequestMapping("/children")
    public ResponseEntity showChildren(@RequestParam Long userId){
        List<ChildDTO> childDTOList = userService.selectOwnChildren(userId);
        if(!CollectionUtils.isEmpty(childDTOList)) {
            List<ChildVO> childVOList = ChildVoToDtoUtil.childDtoToVoList(childDTOList);
            return ResponseView.success(childVOList);
        }
        return ResponseView.success(null, "您还没有添加过孩子哦");
    }

    @RequestMapping(value = "/upLoad", method = RequestMethod.POST)
    public ResponseEntity upLoad(@RequestParam MultipartFile uploadFile){
        String message = uploadFile != null ? "获取文件成功" : "获取文件失败";
        return ResponseView.success(message);
    }


}
