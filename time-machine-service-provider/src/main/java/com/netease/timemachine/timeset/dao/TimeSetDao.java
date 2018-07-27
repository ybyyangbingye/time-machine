package com.netease.timemachine.timeset.dao;

import com.netease.timemachine.moment.meta.Moment;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/26 15:35
 */

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
     * 根据用户userId获取当前时间前一个月所有记录
     *
     * @param childId
     * @return
     */
    @Select("select * from moment where child_id = #{childId} and " +
            "period_diff(date_format(now(),'%Y%m') , date_format(gmt_create, '%Y%m')) =1")
    public List<Moment> searchTimeSetByMonth(Long childId);
}
