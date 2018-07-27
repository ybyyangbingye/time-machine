package com.netease.timemachine.account.controller;

import com.alibaba.fastjson.JSONObject;
import com.netease.timemachine.account.dto.ChildDTO;
import com.netease.timemachine.account.dto.GroupDTO;
import com.netease.timemachine.account.dto.UserDTO;
import com.netease.timemachine.account.meta.Group;
import com.netease.timemachine.account.meta.User;
import com.netease.timemachine.account.service.ChildService;
import com.netease.timemachine.account.service.GroupService;
import com.netease.timemachine.account.service.UserService;
import com.netease.timemachine.account.util.ChildBirthDay;
import com.netease.timemachine.account.util.ChildVoToDtoUtil;
import com.netease.timemachine.account.util.GroupVoToDtoUtil;
import com.netease.timemachine.account.util.ResponseView;
import com.netease.timemachine.account.vo.ChildVO;
import com.netease.timemachine.account.vo.GroupVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 10:22 2018/7/18
 **/
@RequestMapping("/child")
@RestController
public class ChildController {

    @Autowired
    private ChildService childService;

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseEntity insertChild(@RequestBody ChildVO childVO){
        ChildDTO childDTO = ChildVoToDtoUtil.childVoToDto(childVO);
        Long childId = childService.insertChild(childDTO);
        Long userId = childVO.getUserId();
        String identification = childVO.getIdentification();
        String imgUrl = childVO.getImgUrl();
        /**插入新的group记录*/
        GroupDTO groupDTO = new GroupDTO(childId, userId, identification, null, 0, imgUrl);
        groupService.insertGroup(groupDTO);
        return ResponseView.success(null, "添加成功");
    }

    @RequestMapping(value = "/select")
    public ResponseEntity selectChildById(@RequestParam Long childId){
        ChildDTO childDTO = childService.selectChildById(childId);
        ChildVO childVO = ChildVoToDtoUtil.childDtoToVo(childDTO);
        childVO.setAge(ChildBirthDay.getAge(childVO.getBirthDate()));
        return ResponseView.success(childVO);
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
    public ResponseEntity selectOwnChildren(@Param("userId") Long userId, @RequestParam Long childId){
        List<GroupDTO> groupDTOList = groupService.selectGroupByChildId(childId);
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
}
