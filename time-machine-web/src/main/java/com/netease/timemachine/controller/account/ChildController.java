package com.netease.timemachine.controller.account;

import com.netease.timemachine.dto.ChildDTO;
import com.netease.timemachine.service.ChildService;
import com.netease.timemachine.util.account.ChildVoToDtoUtil;
import com.netease.timemachine.util.account.ResponseView;
import com.netease.timemachine.vo.account.ChildVO;
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

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseEntity insertChild(@RequestBody ChildVO childVO){
        ChildDTO childDTO = ChildVoToDtoUtil.childVoToDto(childVO);
        Long childId = childService.insertChild(childDTO);
        childService.insertGroup(childId, childVO.getUserId());
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
