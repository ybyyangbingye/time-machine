package com.netease.timemachine.account.dao;

import com.netease.timemachine.account.meta.Group;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 13:45 2018/7/20
 **/
@Mapper
public interface GroupDao {
    /**
     * 插入新的一条用户-孩子关联记录
     * @param group
     */
    @Insert("insert into user_child_group (child_id,user_id,identification,permission) " +
            "values(#{childId},#{userId},#{identification},#{permission}")
    void insertGroup(Group group);
}