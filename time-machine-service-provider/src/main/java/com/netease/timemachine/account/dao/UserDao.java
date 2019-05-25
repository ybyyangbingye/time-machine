package com.netease.timemachine.account.dao;

import com.netease.timemachine.account.dto.UserDTO;
import com.netease.timemachine.account.meta.Child;
import com.netease.timemachine.account.meta.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

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
    @Insert("insert into user(user_name,phone,imgUrl,create_time) " +
            "values(#{userName},#{phone},#{imgUrl},#{createTime})")
    void insertUser(User user);

    /**
     * 通过phone去查询用户信息
     * @param phone
     */
    @Select("select * from user where phone = #{phone}")
    User selectByPhone(String phone);

    /**
     * 通过userId去查询用户信息
     * @param userId
     * @return
     */
    @Select("select * from user where user_id = #{userId}")
    User selectById(Long userId);

    /**
     * 查询用户拥有的所有孩子id
     * @param userId
     * @return
     */
    @Select("select c.child_id, c.gender, c.child_name, " +
            "c.imgUrl, c.birth_date from child c,user_child_group u where u.child_id = c.child_id and u.user_id=#{userId}")
    List<Child> selectOwnChildren(long userId);

    /**
     * 编辑更新用户
     * @param user
     */
    @Update("update user set user_name=#{userName},imgUrl=#{imgUrl} where user_id = #{userId}")
    void updateUser(User user);

    /**
     * 根据id集合获取用户信息集合
     * @param ids
     * @return
     */
    @Select({
        "<script>",
            "select",
            "user_id, user_name, phone, imgUrl",
            "from user",
            "where user_id in",
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
                "#{id}",
            "</foreach>",
        "</script>"
    })
    List<UserDTO> listUsersByIds(@Param("ids") List<Long> ids);
}
