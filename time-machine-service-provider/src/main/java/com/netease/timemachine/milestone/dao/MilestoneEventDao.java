package com.netease.timemachine.milestone.dao;

import com.netease.timemachine.milestone.dto.MilestoneEventDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午6:56
 */
@Mapper
public interface MilestoneEventDao {

    @Insert("insert into milestone_event(milestone_id, content, location, time, gmt_create)" +
            "values(#{milestoneId}, #{content}, #{location}, #{time}, #{gmtCreate})")
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

    /***
     * 根据事件id获取事件对象
     * @param id
     * @return
     */
    @Select("select id, milestone_id, location, time, gmt_create, gmt_modified from milestone_event where id = #{id}")
    MilestoneEventDTO getMilestoneEventById(long id);

    /**
     * 根据事件id修改事件
     * @param milestoneEventDTO
     * @return
     */
    @Update("update milestone_event set location = #{location}, time = #{time} where id = #{id}")
    boolean modifyMilestoneEventById(MilestoneEventDTO milestoneEventDTO);

    /**
     * 根据用户id获取里程碑事件id和内容
     * @param userId
     * @return
     */
    @Select("select me.id, me.content from milestone m, milestone_event me where m.user_id = #{userId} and m.id = me.id")
    List<MilestoneEventDTO> listMilestoneEventByUserId(long userId);
}
