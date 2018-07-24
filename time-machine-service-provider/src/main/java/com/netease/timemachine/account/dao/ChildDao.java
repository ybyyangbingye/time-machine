package com.netease.timemachine.account.dao;

import com.netease.timemachine.account.meta.Child;
import org.apache.ibatis.annotations.*;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 19:44 2018/7/17
 **/
@Mapper
public interface ChildDao {
    /**
     * 新增孩子信息
     * @param child
     */
    @Insert("insert into child(gender,child_name,imgUrl,birth_date) values" +
            "(#{gender},#{childName},#{imgUrl},#{birthDate})")
    @Options(useGeneratedKeys = true, keyProperty = "childId")
    void insertChild(Child child);

    /**
     * 根据孩子id获取孩子信息
     * @param childId
     */
    @Select("select * from child where child_id = #{childId}")
    Child selectChildById(Long childId);

    /**
     * 更新孩子信息
     * @param child
     */
    @Update("update child set gender=#{gender},child_name=#{childName},imgUrl=#{imgUrl},birth_date=#{birthDate} where child_id = #{childId}")
    void updateChild(Child child);
}
