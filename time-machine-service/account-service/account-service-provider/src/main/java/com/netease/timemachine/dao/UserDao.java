package com.netease.timemachine.dao;

import com.netease.timemachine.meta.Child;
import com.netease.timemachine.meta.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 15:11 2018/7/16
 **/
@Mapper
public interface UserDao {
    /**
     * 插入User对象
     * @param user
     */
    @Insert("insert into user(user_name,phone,identification,address,imgUrl,create_time) " +
            "values(#{userName},#{phone},#{identification},#{address},#{imgUrl},#{createTime})")
    void insertUser(User user);

    /**
     * 通过phone去查询用户信息
     * @param phone
     */
    @Select("select * from user where phone = #{phone}")
    User selectByPhone(String phone);

    /**
     * 查询用户拥有的所有孩子id
     * @param userId
     * @return
     */
    @Select("SELECT c.child_id, c.gender, c.child_name, c.imgUrl, c.birth_date from child c,user_child_group u where u.child_id = c.child_id and u.user_id=#{userId}")
    List<Child> selectOwnChildren(long userId);

    /**
     * 编辑更新用户
     * @param user
     */
    @Update("update user set user_name=#{userName},address=#{address},imgUrl=#{imgUrl} where user_id = #{userId}")
    void updateUser(User user);

}
