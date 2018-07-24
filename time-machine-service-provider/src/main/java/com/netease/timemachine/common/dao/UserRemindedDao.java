package com.netease.timemachine.common.dao;

import com.netease.timemachine.common.dto.UserRemindedDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/24 下午1:36
 */
@Mapper
public interface UserRemindedDao {

    /**
     * 添加被提醒人
     * @param userRemindedDTO
     * @return
     */
    @Insert("insert into user_reminded(user_id, group_id, group_type, gmt_create)" +
            "values(#{userId}, #{groupId}, #{groupType}, #{gmtCreate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    boolean addUserReminded(UserRemindedDTO userRemindedDTO);

    /**
     * 获取动态下的被提醒人
     * @param groupType
     * @param groupId
     * @return
     */
    @Select("select id, user_id, group_id, group_type, gmt_create, gmt_modified from user_reminded where group_type = #{groupType} and group_id = #{groupId}")
    List<UserRemindedDTO> getByGroupTypeAndGroupId(@Param("groupType") int groupType, @Param("groupId") long groupId);

    /**
     * 删除指定的某个被提醒人
     * @param userId
     * @param groupType
     * @param groupId
     * @return
     */
    @Delete("delete from user_reminded where user_id = #{userId} and group_type = #{groupType} and group_id = #{groupId}")
    boolean deleteUserRemindedByUserIdAndGroupTypeAndGroupId(@Param("userId") long userId, @Param("groupType") int groupType, @Param("groupId") long groupId);

    /**
     * 根据组类型和组id删除下面的所有被提醒人
     * @param groupType
     * @param groupId
     * @return
     */
    @Delete("delete from user_reminded where group_type = #{groupType} and group_id = #{groupId}")
    boolean deleteUserRemindedByGroupTypeAndGroupId(@Param("groupType") int groupType, @Param("groupId") long groupId);

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    @Delete("delete from user_reminded where id = #{id}")
    boolean deleteUserRemindedById(long id);
}
