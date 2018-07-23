package com.netease.timemachine.milestone.dao;

import com.netease.timemachine.milestone.dto.MilestoneEventDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

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
}
