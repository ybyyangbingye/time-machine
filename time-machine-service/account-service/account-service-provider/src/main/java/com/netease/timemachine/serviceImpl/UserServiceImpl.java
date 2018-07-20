package com.netease.timemachine.serviceImpl;

import com.netease.timemachine.dao.UserDao;
import com.netease.timemachine.dto.ChildDTO;
import com.netease.timemachine.dto.UserDTO;
import com.netease.timemachine.meta.Child;
import com.netease.timemachine.meta.User;
import com.netease.timemachine.service.UserService;
import com.netease.timemachine.util.ChildDtoToMetaUtil;
import com.netease.timemachine.util.UserDtoToMetaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 15:27 2018/7/16
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void insertUser(UserDTO userDTO) {
        User user = UserDtoToMetaUtil.UserDtoToMeta(userDTO);
        user.setUserName(user.getPhone());
        user.setCreateTime(new Date());
        userDao.insertUser(user);
    }

    @Override
    public UserDTO selectByPhone(String phone) {
        User user = userDao.selectByPhone(phone);
        return UserDtoToMetaUtil.MetaToUserDto(user);
    }

    @Override
    public List<ChildDTO> selectOwnChildren(long userId) {
        List<Child> children = userDao.selectOwnChildren(userId);
        return ChildDtoToMetaUtil.childMetaToDTOList(children);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        User user = UserDtoToMetaUtil.UserDtoToMeta(userDTO);
        userDao.updateUser(user);
    }

}