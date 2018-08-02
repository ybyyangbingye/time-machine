package com.netease.timemachine.timeset.dao;

import com.netease.timemachine.moment.meta.Label;
import com.netease.timemachine.moment.meta.Resource;
import com.netease.timemachine.timeset.dto.TimeSetDTO;
import com.netease.timemachine.timeset.meta.TimeSetByLabel;
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
    List<HashMap> searchLastMonthByViewsTest(Long childId);

    @Select("select * from label as l, label_belonged as b " +
            "where period_diff(date_format(now(),'%Y%m') , date_format(b.gmt_create, '%Y%m')) =1 " +
            "and l.child_id=#{childId} and b.label_id=l.id")
    List<TimeSetByLabel> searchLastMonthByLabelsTest(Long childId);

    /**
     * 通过孩子id来获取上个月状态、里程牌的图片，排序规则为浏览量
     * 返回图片资源地址、浏览量
     * @param childId
     * @return
     */
    @Select("select r.resource_obj, r.views " +
            "from moment m inner join resource r on m.moment_id=r.group_id " +
            "where m.child_id = 6 and r.resource_type = 1 " +
            "and period_diff(date_format(now(),'%Y%m') , date_format(m.gmt_create, '%Y%m')) =1 order by r.views desc")
    @ResultType(HashMap.class)
    List<HashMap> searchLastMonthByViews(Long childId);

    /**
     * 筛选label表
     * @param childId
     * @return
     */
    @Select("select * from label " +
            "where period_diff(date_format(now(),'%Y%m') , date_format(gmt_create, '%Y%m')) =1 " +
            "and child_id=#{childId}")
    @Results({
            @Result(id = true, column = "id", property = "labelId"),
            @Result(column = "group_id", property = "groupId"),
            @Result(column = "name", property = "labelName"),
            @Result(column = "type", property = "labelType"),
            @Result(column = "child_id", property = "childId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<Label> searchLastMonthByLabels(Long childId);


    /**
     * 获取资源
     * @param groupId
     * @return
     */
    @Select("select * from resource where resource_type = 1 and group_id = #{groupId}")
    @Results({
            @Result(id = true, column = "id", property = "file_id"),
            @Result(column = "resource_obj", property = "resource_obj"),
            @Result(column = "resource_type", property = "resource_type"),
            @Result(column = "group_id", property = "group_id"),
            @Result(column = "gmt_create", property = "gmt_create"),
            @Result(column = "gmt_modified", property = "gmt_modified"),
            @Result(column = "views", property = "views")
    })
    List<Resource> searchByGroupId(@Param("groupId") Long groupId);

    /**
     * 插入一条时光集记录，返回主键
     * @param timeSetDTO
     */
    @Insert("insert into timeset (set_name,child_id,musicUrl) values (#{setName},#{childId},#{musicUrl})")
    @Options(useGeneratedKeys = true, keyProperty = "setId", keyColumn = "set_id")
    void addTimeSet(TimeSetDTO timeSetDTO);

    /**
     * 查询时光集是否已经生成
     * @param setName
     * @return
     */
    @Select("select count(*) from timeset where set_name = #{setName} and child_id = #{childId}")
    boolean isExist(@Param("setName") String setName, @Param("childId") Long childId);

    /**
     * 随机获取一条时光集音乐
     * @return
     */
    @Select("select resource_obj from resource where resource_type=3 order by rand() limit 1")
    String musicRanByTimeSet();

    /**
     * 新增一条时光集，然后一次向resource表插入多个数据
     * resource_type=3 音频
     * group_type=3 时光集
     * @param pictures
     * @param groupId
     */
    @Insert("<script>"
    + "insert into resource "
    + "(resource_obj,resource_type,group_id)"
    + "VALUES"
    + "<foreach item='item' index='index' collection='pictures' open='(' separator=',' close=')'>"
    + "#{item},3,#{groupId}"
    + "</foreach>"
    + "</script>")
    void addTimeSetToResource(@Param("pictures") List<String> pictures, @Param("groupId") Long groupId);

    /**
     * 获取已经存在的时光集（时间降序）
     * @param childId
     * @return
     */
    @Select("select * from timeset where child_id = #{childId} order by create_time desc")
    List<TimeSetDTO> selectTimeSetById(Long childId);

    /**
     * 查询某个时光集的所有图片
     * @param setId
     * @return
     */
    @Select("select resource_obj from resource where resource_type=3 and group_id=#{setId}")
    List<String> selectTimeSetResources(Long setId);
}
