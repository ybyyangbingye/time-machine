package com.netease.timemachine.moment.dao;

import com.netease.timemachine.moment.meta.Moment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/24 10:58
 */

@Mapper
public interface MomentDao {

    /**
     * 根据页码获取用户宝宝的五条状态
     * @param userId
     * @param childId
     * @param start
     * @param end
     * @return
     */
    @Select("select * from moment where creator_id = #{userId} and child_id=#{childId} limit #{start},#{end}")
    List<Moment> getMoments(@Param("userId")Long userId,
                             @Param("childId")Long childId,
                             @Param("start")Long start,
                             @Param("end")Long end);

    /**
     * 获取一条状态下的所有图片或视频
     * @param groupId
     * @return
     */
    @Select("select resource_obj from resource where group_id=#{groupId} and group_type=2")
    List<String> getMomentFiles(@Param("groupId")Long groupId);

    /**
     *
     * @param userId
     * @param childId
     * @param tagId
     * @param description
     * @param files
     * @return
     */
    boolean addMomment(Long userId, Long childId, Long tagId, String description,
                       List<String> files);
}
