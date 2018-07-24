package com.netease.timemachine.account.dao;

import com.netease.timemachine.account.dto.GroupDTO;
import com.netease.timemachine.account.meta.Group;
import org.apache.ibatis.annotations.*;

import java.util.List;

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
    @Insert("insert into user_child_group (child_id,user_id,identification,nick_name,permission,imgUrl)" +
            "values(#{childId},#{userId},#{identification},#{nickName},#{permission},#{imgUrl})")
    void insertGroup(Group group);

    /**
     * 展示具体信息
     * @param userId
     * @param childId
     * @return
     */
    @Select("select * from user_child_group where user_id = #{userId} and child_id = #{childId}")
    Group selectByUserAndChildId(@Param("userId") Long userId, @Param("childId") Long childId);

    /**
     * 删除一条孩子关联
     */
    @Delete("delete from user_child_group where user_id = #{userId} and child_id = #{childId}")
    void deleteGroup(@Param("userId") Long userId, @Param("childId") Long childId);

    /**
     * 更新身份、昵称、权限
     */
    @Update("update user_child_group set identification = #{identification}," +
            "nick_name = #{nickName}, permission = #{permission} where user_id = #{userId} and child_id = #{childId}")
    void UpdateGroup(Group group);

    /**
     * 获取管理childId的所有用户
     * @param childId
     */
    @Select("select * from user_child_group where child_id = #{childId}")
    List<Group> selectGroupByChildId(Long childId);

    /**
     * 修改用户信息时，Group表里面的imgUrl信息也会更新
     * @param imgUrl
     * @param userId
     */
    @Update("update user_child_group set imgUrl=#{imgUrl} where user_id=#{userId}")
    void updateGroupImg(@Param("imgUrl") String imgUrl, @Param("userId") Long userId);

    /**
     * 获取当前用户在当前孩子的管理权限
     * 权限（创建者：0，管理者：1，记录：2， 查看：3）
     * @param userId
     * @param childId
     * @return
     */
    @Select("select permission from user_child_group where user_id = #{userId} and child_id = #{childId}")
    Integer permissionById(@Param("userId") Long userId, @Param("childId") Long childId);
}
