package com.netease.timemachine.dao;

import com.netease.timemachine.meta.Child;
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
    Long insertChild(Child child);

    /**
     * 根据孩子id获取孩子信息
     * @param childId
     */
    @Select("select * from child where child_id = #{childId}")
    Child selectChildById(long childId);

    /**
     * 插入新的一条用户-孩子关联记录
     * @param childId
     * @param userId
     */
    @Insert("insert into user_child_group (child_id,user_id) values(#{childId},#{userId})")
    void insertGroup(@Param("childId") long childId, @Param("userId") long userId);

    @Update("update child set gender=#{gender},child_name=#{childName},imgUrl=#{imgUrl},birth_date=#{birthDate} where child_id = #{childId}")
    void updateChild(Child child);
}
