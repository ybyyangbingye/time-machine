package com.netease.timemachine.timeset.dao;

import com.netease.timemachine.moment.meta.Moment;
import io.micrometer.core.instrument.binder.db.DatabaseTableMetrics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 20:28 2018/7/26
 **/
@Mapper
public interface TimeSetDao {

    /**
     * 根据用户userId获取当前时间前一个月所有记录
     * @param childId
     * @return
     */
    @Select("select * from moment where child_id = #{childId} and " +
            "period_diff(date_format(now(),'%Y%m') , date_format(gmt_create, '%Y%m')) =1")
    public List<Moment> searchTimeSetByMonth(Long childId);




}
