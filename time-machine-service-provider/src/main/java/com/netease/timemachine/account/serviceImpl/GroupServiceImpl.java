package com.netease.timemachine.account.serviceImpl;

import com.netease.timemachine.account.dao.GroupDao;
import com.netease.timemachine.account.dto.GroupDTO;
import com.netease.timemachine.account.meta.Group;
import com.netease.timemachine.account.service.GroupService;
import com.netease.timemachine.account.util.GroupDtoToMetaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void deleteGroup(Long userId, Long childId) {
        groupDao.deleteGroup(userId, childId);
    }

    @Override
    public void UpdateGroup(GroupDTO groupDTO) {
        Group group = GroupDtoToMetaUtil.GroupDtoToMeta(groupDTO);
        groupDao.UpdateGroup(group);
    }

    @Override
    public GroupDTO selectByUserAndChildId(Long userId, Long childId) {
        Group group = groupDao.selectByUserAndChildId(userId, childId);
        return GroupDtoToMetaUtil.GroupMetaToDTO(group);
    }

    @Override
    public List<GroupDTO> selectGroupByChildId(Long childId) {
        List<Group> groupList = groupDao.selectGroupByChildId(childId);
        List<GroupDTO> groupDTOList = GroupDtoToMetaUtil.GroupMetaToDtoList(groupList);
        return groupDTOList;
    }

    @Override
    public void updateGroupImg(String imgUrl, Long userId) {
        groupDao.updateGroupImg(imgUrl, userId);
    }

    @Override
    public Integer permissionById(Long userId, Long childId) {
        return groupDao.permissionById(userId, childId);
    }
}
