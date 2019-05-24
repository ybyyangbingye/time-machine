package com.netease.timemachine.account.controller;

import com.alibaba.fastjson.JSONObject;
import com.netease.timemachine.account.dto.ChildDTO;
import com.netease.timemachine.account.dto.GroupDTO;
import com.netease.timemachine.account.service.ChildService;
import com.netease.timemachine.account.service.GroupService;
import com.netease.timemachine.account.service.UserService;
import com.netease.timemachine.account.util.*;
import com.netease.timemachine.account.vo.ChildVO;
import com.netease.timemachine.account.vo.GroupVO;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.netease.timemachine.account.enums.AccountEnum.CHILD_NULL;
import static com.netease.timemachine.account.enums.AccountEnum.CHILD_REPEAT;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 10:22 2018/7/18
 **/
@RequestMapping("/child")
@RestController
public class ChildController {

    private static final String DEFAULT_BABY_PIC = "http://time-machine.nos-eastchina1.126.net/default/baby.jpg";

    @Resource
    private ChildService childService;

    @Resource
    private GroupService groupService;

    @Resource
    private UserService userService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseEntity insertChild(@RequestBody ChildVO childVO){
        if(StringUtils.isEmpty(childVO.getImgUrl())){
            childVO.setImgUrl(DEFAULT_BABY_PIC);
        }
        Long userId = childVO.getUserId();
        if(userId != null) {
            List<ChildDTO> childDTOList = userService.selectOwnChildren(userId);
            if (!CollectionUtils.isEmpty(childDTOList)) {
                for (ChildDTO childDTO : childDTOList) {
                    if (childVO.getChildName().equals(childDTO.getChildName())) {
                        return ResponseView.fail(CHILD_REPEAT.getCode(), CHILD_REPEAT.getMessage());
                    }
                }
            }
        }
        ChildDTO childDTO = ChildVoToDtoUtil.childVoToDto(childVO);
        Long childId = childService.insertChild(childDTO);
        childVO.setChildId(childId);
        childVO.setAge(ChildBirthDay.getAge(childVO.getBirthDate()));
        String identification = childVO.getIdentification();
        String imgUrl = childVO.getImgUrl();
        /**插入新的group记录*/
        GroupDTO groupDTO = new GroupDTO(childId, userId, identification, identification, 0, imgUrl);
        groupService.insertGroup(groupDTO);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("child", childVO);
        jsonObject.put("invitationCode", ChildInvitationCode.inviCodeGenerator(childId));
        return ResponseView.success(jsonObject, "添加成功");
    }

    @RequestMapping(value = "/select")
    public ResponseEntity selectChildById(@RequestParam Long childId){
        ChildDTO childDTO = childService.selectChildById(childId);
        if(childDTO != null) {
            ChildVO childVO = ChildVoToDtoUtil.childDtoToVo(childDTO);
            String age = ChildBirthDay.getAge(childVO.getBirthDate());
            if(age != null){
                childVO.setAge(age);
            }
            return ResponseView.success(childVO);
        }else {
            return ResponseView.fail(CHILD_NULL.getCode(), CHILD_NULL.getMessage());
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity updateChild(@RequestBody ChildVO childVO){
        ChildDTO childDTO = ChildVoToDtoUtil.childVoToDto(childVO);
        childService.updateChild(childDTO);
        return ResponseView.success(null, "更新成功");
    }

    /**
     * 展示某个孩子的所有关注者，并且返回当前用户对该孩子的权限
     * @param userId
     * @param childId
     * @return
     */
    @RequestMapping("/ownManagers")
    public ResponseEntity selectOwnChildren(@RequestParam("userId") Long userId,
                                            @RequestParam("childId") Long childId){
        List<GroupDTO> groupDTOList = groupService.selectGroupByChildId(childId);
        GroupDTO groupDTO = null;
        for(int i = 0; i < groupDTOList.size(); i++){
            groupDTO = groupDTOList.get(i);
            if(groupDTO.getUserId().equals(userId)){
                groupDTOList.remove(i);
                break;
            }
        }
        groupDTOList.add(0, groupDTO);
        List<GroupVO> groupVOList = GroupVoToDtoUtil.GroupDtoToVoList(groupDTOList);
        Integer permission = groupService.permissionById(userId, childId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("permission", permission);
        jsonObject.put("groupVOList", groupVOList);
        return ResponseView.success(jsonObject);
    }

    /**
     * 展示某孩子某管理者的具体信息（身份、昵称、权限）
     * @param userId
     * @param childId
     * @return
     */
    @RequestMapping("/manager/detail")
    public ResponseEntity detailManager(@RequestParam("userId")Long userId,
                                        @RequestParam("childId")Long childId){
        GroupDTO groupDTO = groupService.selectByUserAndChildId(userId, childId);
        GroupVO GroupVO = GroupVoToDtoUtil.GroupDtoToVo(groupDTO);
        return ResponseView.success(GroupVO);
    }

    /**
     * 更新某孩子某管理者的具体信息（身份、昵称、权限）
     * 管理者才可以修改，但是不能修改创建者权限，可以修改创建者身份和昵称
     * @param groupVO
     * @return
     */
    @RequestMapping("/manager/update")
    public ResponseEntity updateManager(@RequestBody GroupVO groupVO){
        GroupDTO groupDTO = GroupVoToDtoUtil.GroupVoToDto(groupVO);
        groupService.UpdateGroup(groupDTO);
        return ResponseView.success(null, "更新成功");
    }

    @RequestMapping("/manager/delete")
    public ResponseEntity deleteManager(@RequestParam("userId")Long userId,
                                        @RequestParam("childId")Long childId){
        groupService.deleteGroup(userId, childId);
        return ResponseView.success(null, "删除成功");
    }

    /**
     * 根据孩子id获取邀请码
     * @param childId
     * @return
     */
    @RequestMapping(value = "/getCode", method = RequestMethod.POST)
    public ResponseEntity getChildCode(@RequestParam Long childId){
        String invitationCode = ChildInvitationCode.inviCodeGenerator(childId);
        return ResponseView.success(invitationCode);
    }

    /**
     * 根据邀请码获取孩子信息(引导页和app内部)
     * @param invitationCode
     * @return
     */
    @RequestMapping(value = "/detailByCode", method = RequestMethod.POST)
    public ResponseEntity childDetailByCode(@RequestParam String invitationCode){
        Long childId = ChildInvitationCode.inviDecoding(invitationCode);
        ChildDTO childDTO = childService.selectChildById(childId);
        if(childDTO == null){
            return ResponseView.fail(CHILD_NULL.getCode(), CHILD_NULL.getMessage());
        }
        ChildVO childVO = ChildVoToDtoUtil.childDtoToVo(childDTO);
        childVO.setAge(ChildBirthDay.getAge(childVO.getBirthDate()));
        return ResponseView.success(childVO);
    }
}
