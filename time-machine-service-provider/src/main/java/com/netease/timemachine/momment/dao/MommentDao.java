package com.netease.timemachine.momment.dao;

import com.netease.timemachine.momment.meta.Momment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/24 10:58
 */

@Mapper
public interface MommentDao {

    /**
     * 根据页码获取用户宝宝的五条状态
     * @param userId
     * @param childId
     * @param start
     * @param end
     * @return
     */
    @Select("select * from momment where creator_id = #{userId} and child_id=#{childId} limit #{start},#{end}")
    List<Momment> getMomments(@Param("userId")Long userId,
                              @Param("childId")Long childId,
                              @Param("start")Long start,
                              @Param("end")Long end);

    /**
     *
     * @param groupId
     * @return
     */
    @Select("select resource_obj from resource where group_id=#{groupId} and group_type=2")
    List<String> getMommentFiles(@Param("groupId")Long groupId);


}
