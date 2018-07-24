package com.netease.timemachine.common.serviceImpl;

import com.netease.timemachine.common.dao.UserRemindedDao;
import com.netease.timemachine.common.dto.UserRemindedDTO;
import com.netease.timemachine.common.service.UserRemindedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/24 下午1:47
 */
@Service
public class UserRemindedServiceImpl implements UserRemindedService{

    @Autowired
    private UserRemindedDao userRemindedDao;
    @Override
    public boolean addUserReminded(UserRemindedDTO userRemindedDTO) {
        return userRemindedDao.addUserReminded(userRemindedDTO);
    }

    @Override
    public List<UserRemindedDTO> getByGroupTypeAndGroupId(int groupType, long groupId) {
        return userRemindedDao.getByGroupTypeAndGroupId(groupType, groupId);
    }

    @Override
    public boolean deleteUserRemindedByUserIdAndGroupTypeAndGroupId(long userId, int groupType, long groupId) {
        return userRemindedDao.deleteUserRemindedByUserIdAndGroupTypeAndGroupId(userId, groupType, groupId);
    }

    @Override
    public boolean deleteUserRemindedByGroupTypeAndGroupId(int groupType, long groupId) {
        return userRemindedDao.deleteUserRemindedByGroupTypeAndGroupId(groupType, groupId);
    }

    @Override
    public boolean deleteUserRemindedById(long id) {
        return userRemindedDao.deleteUserRemindedById(id);
    }
}
