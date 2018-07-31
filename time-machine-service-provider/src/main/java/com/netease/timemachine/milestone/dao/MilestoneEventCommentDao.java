package com.netease.timemachine.milestone.dao;

import com.netease.timemachine.milestone.dto.MilestoneEventCommentDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/31 下午7:39
 */
@Mapper
public interface MilestoneEventCommentDao {


    @Select("select count(*) from milestoneEventComment where group_id = #{gorupId}")
    int getCommentCountByGroupId(long groupId);

    /**
     * 添加评论
     * @param milestoneEventCommentDTO
     * @return
     */
    @Insert("insert into milestone_event_comment(group_id, content, from, to, gmt_create)" +
            "values(#{groupId}, #{content}, #{from}, #{to}, #{gmtCreate})")
    boolean addMilestoneEventComment(MilestoneEventCommentDTO milestoneEventCommentDTO);
}
