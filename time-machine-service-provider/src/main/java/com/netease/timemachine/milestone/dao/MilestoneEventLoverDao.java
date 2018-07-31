package com.netease.timemachine.milestone.dao;

import com.netease.timemachine.milestone.dto.MilestoneEventLoverDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/31 下午8:15
 */
@Mapper
public interface MilestoneEventLoverDao {

    @Select("select count(*) from milestone_event_lover where group_id = #{groupId}")
    int getLoverCountByGroupId(long groupId);

    @Insert("insert into milestone_event_lover(group_id, from, gmt_create" +
            "values(#{groupId}, #{from}, #{gmtCreate}")
    boolean addMilestoneEventLover(MilestoneEventLoverDTO milestoneEventLoverDTO);
}
