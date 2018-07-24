package com.netease.timemachine.milestone.dao;

import com.netease.timemachine.milestone.dto.MilestoneDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午4:35
 */
@Mapper
public interface MilestoneDao {

    @Insert("insert into milestone(name, time, child_id, user_id, gmt_create) " +
            "values(#{name}, #{time}, #{childId}, #{userId}, #{gmtCreate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    boolean addMilestone(MilestoneDTO milestoneDTO);

    /**
     * 获取里程碑列表
     * @param childId
     * @return
     */
    @Select("select id, name, time, child_id, user_id, gmt_create, gmt_modified from milestone where child_id = #{childId}")
    List<MilestoneDTO> getMilestoneList(long childId);
}
