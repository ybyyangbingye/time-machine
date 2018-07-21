package com.netease.timemachine.serviceImpl;

import com.netease.timemachine.dao.GroupDao;
import com.netease.timemachine.dto.GroupDTO;
import com.netease.timemachine.meta.Group;
import com.netease.timemachine.service.GroupService;
import com.netease.timemachine.util.GroupDtoToMetaUtil;
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
