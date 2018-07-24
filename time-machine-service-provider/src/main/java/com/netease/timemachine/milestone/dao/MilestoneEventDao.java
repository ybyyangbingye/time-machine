package com.netease.timemachine.milestone.dao;

import com.netease.timemachine.milestone.dto.MilestoneEventDTO;
import org.apache.ibatis.annotations.*;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午6:56
 */
@Mapper
public interface MilestoneEventDao {

    @Insert("insert into milestone_event(milestone_id, location, time, gmt_create)" +
            "values(#{milestoneId}, #{location}, #{time}, #{gmtCreate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    boolean addMilestoneEvent(MilestoneEventDTO milestoneEventDTO);

    @Select("select id, milestone_id, location, time, gmt_create, gmt_modified from milestone_event where milestone_id = #{milestoneId}")
    MilestoneEventDTO getMilestoneEventByMilestoneId(long milestoneId);

    /**
     * 根据里程碑事件id删除里程碑对应的事件
     *
     * @param milestoneEventId
     * @return
     */
    @Delete("delete from milestone_event where id = #{milestoneEventId}")
    boolean deleteMilestoneEventById(long milestoneEventId);
}
