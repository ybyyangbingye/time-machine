package com.netease.timemachine.moment.dao;

import com.netease.timemachine.moment.dto.Givealikevo;
import com.netease.timemachine.moment.meta.Givealike;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: ZLS
 * @Description:
 * @Date: 2018/7/25 19:36
 */
@Mapper
public interface GivealikeDao {
    /**
     *
     * @param momentId
     * @return
     */
    @Select("select child_id from moment where moment_id=#{momentId}")
    Long getChildId(long momentId);

    /**
     *
     * @param childId
     * @param userId
     * @return
     */
    @Select("select nick_name from user_child_group where child_id=#{childId} and user_id=#{userId}")
    String getNickname(@Param("childId") Long childId, @Param("userId") Long userId);

    /**
     *
     * @param givealike
     * @return
     */
    @Insert("insert into givealike(user_id, group_id, nickname) " +
            "values(#{userId}, #{groupId}, #{nickname})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    boolean insertGivealike(Givealike givealike);

    /**
     *
     * @param userId
     * @param groupId
     * @return
     */
    @Delete("delete from givealike where user_id=#{userId} and group_id=#{groupId}")
    boolean deleteGivealike(@Param("userId") Long userId,@Param("groupId") Long groupId);

    /**
     * 获取所有点赞人的nickname
     * @param groupId
     * @return
     */
    @Select("select user_id,nickname from givealike where group_id=#{groupId}")
    List<Givealikevo> getAll(Long groupId);

    /**
     *
     * @param userId
     * @param groupId
     * @return
     */
    @Select("select count(*) from givealike where user_id=#{userId} and group_id=#{groupId}")
    int isGivealike(@Param("userId") Long userId,@Param("groupId") Long groupId);

    /**
     * 根据动态id获取喜欢动态的人数
     * @param groupId
     * @return
     */
    @Select("select count(*) from givealike where group_id = #{groupId}")
    int getLoverCountByMomentId(long groupId);
}
