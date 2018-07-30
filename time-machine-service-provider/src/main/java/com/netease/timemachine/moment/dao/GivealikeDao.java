package com.netease.timemachine.moment.dao;

import com.netease.timemachine.moment.dto.GivealikeDTO;
import com.netease.timemachine.moment.meta.Givealike;
import org.apache.ibatis.annotations.*;

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
    @Insert("insert into givealike(user_id, moment_id, nickname) " +
            "values(#{userId}, #{momentId}, #{nickname})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    boolean insertGivealike(Givealike givealike);

    /**
     *
     * @param userId
     * @param momentId
     * @return
     */
    @Delete("delete from givealike where user_id=#{userId} and moment_id=#{momentId}")
    boolean deleteGivealike(@Param("userId") Long userId,@Param("momentId") Long momentId);
}
