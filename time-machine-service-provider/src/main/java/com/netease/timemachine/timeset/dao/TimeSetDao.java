package com.netease.timemachine.timeset.dao;

import com.netease.timemachine.common.dto.LabelBelongedDTO;
import com.netease.timemachine.moment.meta.Resource;
import com.netease.timemachine.timeset.dto.TimeSetDTO;
import com.netease.timemachine.timeset.meta.TimeSetByLabel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import com.netease.timemachine.moment.meta.Moment;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 20:28 2018/7/26
 **/
@Mapper
public interface TimeSetDao {

    @Select("select city, count(*) as amount from moment group by city where child_id=#{childId} and creator_id=#{userId}")
    @ResultType(HashMap.class)
    List<HashMap> getCities(@Param("childId") Long childId, @Param("userId") Long userId);

    @Select("select moment_id from moment where city=#{city}")
    List<Long> getMomentsByCity(String city);

    @Select("select count(*) from resource where group_id=#{momentId} and group_type=2 group by group_id")
    Long countMomentFiles(Long momentId);


    /**
     * 通过孩子id来获取上个月状态、里程牌的图片，排序规则为浏览量
     * 返回图片资源地址、浏览量
     * @param childId
     * @return
     */
    @Select("(select r.resource_obj, r.views from moment m " +
            "INNER JOIN resource r on m.moment_id=r.group_id " +
            "where m.child_id = #{childId} and r.resource_type = 1 " +
            "and period_diff(date_format(now(),'%Y%m') , date_format(m.gmt_create, '%Y%m')) =1 ORDER BY r.views desc) " +
            "union " +
            "(select r.resource_obj, r.views from resource r,(select e.id, e.time from milestone m INNER JOIN milestone_event e on e.milestone_id = m.id " +
            "where m.child_id = #{childId} and period_diff(date_format(now(),'%Y%m') , date_format(e.gmt_create, '%Y%m')) =1) " +
            "as x where r.group_id= x.id " +
            "and r.resource_type = 1 " +
            "ORDER BY r.views desc) order by views desc")
    @ResultType(HashMap.class)
    List<HashMap> searchLastMonthByViews(Long childId);


    /**
     * 筛选label表和label_belonged表
     * @param childId
     * @return
     */
    @Select("select * from label as l, label_belonged as b " +
            "where date_format(b.gmt_create, '%Y%m') = date_format(now(),'%Y%m') " +
            "and l.child_id=#{childId} and b.label_id=l.id")
    List<TimeSetByLabel> searchLastMonthByLabels(Long childId);


    /**
     * 获取资源
     * @param groupId
     * @param groupType
     * @return
     */
    @Select("select * from resource where resource_type = 1 and group_id = #{groupId} and group_type = #{groupType}")
    @Results({
            @Result(id = true, column = "id", property = "file_id"),
            @Result(column = "resource_obj", property = "resource_obj"),
            @Result(column = "resource_type", property = "resource_type"),
            @Result(column = "group_type", property = "group_type"),
            @Result(column = "group_id", property = "group_id"),
            @Result(column = "gmt_create", property = "gmt_create"),
            @Result(column = "gmt_modified", property = "gmt_modified"),
            @Result(column = "views", property = "views")
    })
    List<Resource> searchByGroupIdAndType(@Param("groupId") Long groupId, @Param("groupType") Integer groupType);

    /**
     * 向资源表插入一条时光集记录
     * resource_type=3 音频
     * group_type=3 时光集
     * @param resourceObj
     */
    @Insert("insert into resource(resource_obj,resource_type,group_type) values "+
            "(#{file},3,3)")
    void addTimeSetFile(@Param("resourceObj")String resourceObj);

    /**
     * 插入一条时光集记录
     * @param timeSetDTO
     */
    @Insert("insert into timeset (set_name,set_type) values (#{setName},#{setType})")
    void addTimeSet(TimeSetDTO timeSetDTO);

    /**
     * 查询时光集是否已经生成
     * @param setName
     * @return
     */
    @Select("select count(*) from timeset where set_name = #{setName}")
    boolean isExist(String setName);

    /**
     * 随机获取一条时光集音乐
     * @return
     */
    @Select("select resource_obj from resource where resource_type=3 and group_type =3 order by rand() limit 1")
    String resourceRanByTimeSet();
}
