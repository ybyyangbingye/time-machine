package com.netease.timemachine.moment.dao;

import com.netease.timemachine.moment.meta.Moment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/24 10:58
 */

@Mapper
public interface MomentDao {

    /**
     * 根据页码获取用户宝宝的五条状态
     * @param userId
     * @param childId
     * @param start
     * @param end
     * @return
     */
    @Select("select * from moment where creator_id = #{userId} and child_id=#{childId} limit #{start},#{end}")
    List<Moment> getMoments(@Param("userId")Long userId,
                             @Param("childId")Long childId,
                             @Param("start")Long start,
                             @Param("end")Long end);

    /**
     * 获取一条状态下的所有图片或视频
     * @param groupId
     * @return
     */
    @Select("select resource_obj from resource where group_id=#{groupId} and group_type=2")
    List<String> getMomentFiles(@Param("groupId")Long groupId);


    /**
     * 用户添加状态
     * @param moment
     */
    @Insert("insert into moment(creator_id,description,location,child_id) values" +
    "(#{creatorId},#{description},#{location},#{childId})")
    @Options(useGeneratedKeys = true, keyProperty = "momentId")
    void addMoment(Moment moment);

    /**
     * 添加状态下的图片/视频
     * todo: resource_type为1，默认插入的是图片，没处理视频
     * @param file
     * @param momentId
     */
    @Insert("insert into resource(resource_obj,resource_type,group_id,group_type) values"+
    "(#{file},1,#{momentId},2)")
    void addFile(@Param("file")String file,@Param("momentId")Long momentId);

    /**
     * 添加状态下的标签
     * @param momentId
     * @param labelId
     */
    @Insert("insert into label_belonged(label_id,group_type,group_id) values"+
    "(#{labelId},2,#{momentId})")
    void addLabel(@Param("momentId")Long momentId, @Param("labelId")Long labelId);

    /**
     * 用户删除某条状态
     * @param momentId
     */
    @Delete("delete from moment where moment_id=#{momentId}")
    void deleteMoment(@Param("momentId")Long momentId);

    /**
     * 更新状态下的某张图片的浏览量
     * @param resourceObj
     */
    @Update("update resource set views = views+1 where resource_obj = #{resourceObj} and  resource_type = 1")
    void incrementViews(String resourceObj);
}
