package com.netease.timemachine.timeset.dao;

import com.netease.timemachine.common.dto.LabelBelongedDTO;
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

    @Select("select city, count(*) as amount from moment group by city where child_id=#{childId}")
//    @Results({
//            @Result(property = "city",column = "city",javaType = String.class),
//            @Result(property = "amount",column = "amount",javaType = Long.class)
//    })
    @ResultType(HashMap.class)
    List<HashMap> getCities(Long childId);

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
     * 获取当前月份的所有label_belonged
     * @return
     */
    @Select("select * from label_belonged where date_format(gmt_create, '%Y%m') = date_format(now(),'%Y%m')")
    List<LabelBelongedDTO> searchNowMonthByLabels();

    /**
     * 根据name去获取
     * @param labelId
     * @param childId
     * @return
     */
    @Select("select name from label where id = #{labelId} and child_id = #{childId}")
    List<String> searchLabelName(@Param("labelId") Long labelId, @Param("childId") Long childId);

    /**
     * 根据
     * @param groupType
     * @param groupId
     * @return
     */
    @Select("select resource_obj, views from resource where group_type = #{groupType} and group_id = #{groupId} order by views desc")
    List<HashMap> searchImgOfLabel(Long groupType, Long groupId);

}
