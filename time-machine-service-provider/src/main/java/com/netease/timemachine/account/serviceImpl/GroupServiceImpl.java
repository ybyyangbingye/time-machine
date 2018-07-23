package com.netease.timemachine.account.serviceImpl;

import com.netease.timemachine.account.dao.GroupDao;
import com.netease.timemachine.account.dto.GroupDTO;
import com.netease.timemachine.account.meta.Group;
import com.netease.timemachine.account.service.GroupService;
import com.netease.timemachine.account.util.GroupDtoToMetaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 15:10 2018/7/20
 **/
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Override
    public void insertGroup(GroupDTO groupDTO) {
        Group group = GroupDtoToMetaUtil.GroupDtoToMeta(groupDTO);
        groupDao.insertGroup(group);
    }
}
