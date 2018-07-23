package com.netease.timemachine.account.serviceImpl;

import com.netease.timemachine.account.dao.ChildDao;
import com.netease.timemachine.account.dto.ChildDTO;
import com.netease.timemachine.account.meta.Child;
import com.netease.timemachine.account.service.ChildService;
import com.netease.timemachine.account.util.ChildDtoToMetaUtil;
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
    public void updateChild(ChildDTO childDTO) {
        Child child = ChildDtoToMetaUtil.childDtoToMeta(childDTO);
        childDao.updateChild(child);
    }
}
