package com.netease.timemachine.account.serviceImpl;

import com.netease.timemachine.account.dao.UserDao;
import com.netease.timemachine.account.dto.ChildDTO;
import com.netease.timemachine.account.dto.UserDTO;
import com.netease.timemachine.account.meta.Child;
import com.netease.timemachine.account.meta.User;
import com.netease.timemachine.account.service.UserService;
import com.netease.timemachine.account.util.ChildDtoToMetaUtil;
import com.netease.timemachine.account.util.UserDtoToMetaUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 15:27 2018/7/16
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public void insertUser(UserDTO userDTO) {
        User user = UserDtoToMetaUtil.UserDtoToMeta(userDTO);
        user.setUserName(user.getPhone());
        user.setImgUrl("http://time-machine.nos-eastchina1.126.net/default/user.jpg");
        userDao.insertUser(user);
    }

    @Override
    public UserDTO selectByPhone(String phone) {
        User user = userDao.selectByPhone(phone);
        return UserDtoToMetaUtil.MetaToUserDto(user);
    }

    @Override
    public List<ChildDTO> selectOwnChildren(Long userId) {
        List<Child> children = userDao.selectOwnChildren(userId);
        return ChildDtoToMetaUtil.childMetaToDTOList(children);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        User user = UserDtoToMetaUtil.UserDtoToMeta(userDTO);
        userDao.updateUser(user);
    }

    @Override
    public List<UserDTO> listUsersByIds(List<Long> ids) {
        return userDao.listUsersByIds(ids);
    }

    @Override
    public UserDTO selectById(Long userId) {
        return UserDtoToMetaUtil.MetaToUserDto(userDao.selectById(userId));
    }
}
