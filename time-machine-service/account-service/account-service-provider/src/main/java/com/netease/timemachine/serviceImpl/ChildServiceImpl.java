package com.netease.timemachine.serviceImpl;

import com.netease.timemachine.dao.ChildDao;
import com.netease.timemachine.dto.ChildDTO;
import com.netease.timemachine.meta.Child;
import com.netease.timemachine.service.ChildService;
import com.netease.timemachine.util.ChildDtoToMetaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 20:16 2018/7/17
 **/
@Service
public class ChildServiceImpl implements ChildService {

    @Autowired
    private ChildDao childDao;

    @Override
    public Long insertChild(ChildDTO childDTO) {
        Child child = ChildDtoToMetaUtil.childDtoToMeta(childDTO);
        childDao.insertChild(child);
        return child.getChildId();
    }

    @Override
    public ChildDTO selectChildById(long childId) {
        Child child = childDao.selectChildById(childId);
        return ChildDtoToMetaUtil.childMetaToDto(child);
    }

    @Override
    public void insertGroup(Long childId, Long userId) {
        childDao.insertGroup(childId, userId);
    }

    @Override
    public void updateChild(ChildDTO childDTO) {
        childDao.updateChild(ChildDtoToMetaUtil.childDtoToMeta(childDTO));
    }
}
