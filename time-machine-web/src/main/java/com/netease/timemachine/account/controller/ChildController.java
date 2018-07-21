package com.netease.timemachine.account.controller;

import com.netease.timemachine.dto.ChildDTO;
import com.netease.timemachine.dto.GroupDTO;
import com.netease.timemachine.service.ChildService;
import com.netease.timemachine.service.GroupService;
import com.netease.timemachine.account.util.ChildVoToDtoUtil;
import com.netease.timemachine.account.util.ResponseView;
import com.netease.timemachine.account.vo.ChildVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        GroupDTO groupDTO = new GroupDTO(childId, userId, childVO.getIdentification(), 1);
        groupService.insertGroup(groupDTO);
        return ResponseView.success(null, "添加成功");
    }

    @RequestMapping(value = "/select")
    public ResponseEntity selectChildById(@RequestParam long childId){
        ChildDTO childDTO = childService.selectChildById(childId);
        return ResponseView.success(ChildVoToDtoUtil.childDtoToVo(childDTO));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity updateChild(@RequestBody ChildVO childVO){
        ChildDTO childDTO = ChildVoToDtoUtil.childVoToDto(childVO);
        childService.updateChild(childDTO);
        return ResponseView.success(null, "更新成功");
    }
}
