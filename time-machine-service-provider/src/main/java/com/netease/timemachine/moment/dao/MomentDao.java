package com.netease.timemachine.moment.dao;

import com.netease.timemachine.moment.dto.MomentDTO;
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
     * @param childId
     * @param start
     * @return
     */
    @Select("select * from moment where child_id=#{childId} and group_type=#{type} order by gmt_create desc limit #{start},5")
    List<Moment> getMoments( @Param("childId")Long childId,
                             @Param("start")Long start, @Param("type")Long type);

    /**
     * 获取宝宝所有的里程碑状态
     * @param childId
     * @return
     */
    @Select("select * from moment where child_id=#{childId} and group_type=#{type} order by gmt_create desc")
    List<Moment> getAllMoments( @Param("childId")Long childId, @Param("type")Long type);

    /**
     * 获取一条状态下的所有图片或视频
     * @param groupId
     * @return
     */
    @Select("select resource_obj from resource where group_id=#{groupId}")
    List<String> getMomentFiles(@Param("groupId")Long groupId);


    /**
     * 获取一条状态下的所有标签
     * @param groupId
     * @return
     */
    @Select("select name from label where group_id=#{groupId}")
    List<String> getMomentLabels(@Param("groupId")Long groupId);

    /**
     * 获取昵称
     * @param childId
     * @param userId
     * @return
     */
    @Select("select nick_name from user_child_group where child_id=#{childId} and user_id=#{userId}")
    String getNickname(@Param("childId") Long childId, @Param("userId") Long userId);

    /**
     * 用户添加状态
     * @param moment
     */
    @Insert("insert into moment(group_type,creator_id,description,location,child_id,title) values" +
    "(#{groupType},#{creatorId},#{description},#{location},#{childId},#{title})")
    @Options(useGeneratedKeys = true, keyProperty = "momentId")
    void addMoment(Moment moment);

    /**
     * 添加状态下的图片/视频
     * todo: resource_type为1，默认插入的是图片，没处理视频
     * @param file
     * @param momentId
     */
    @Insert("insert into resource(resource_obj,resource_type,group_id,group_type) values"+
    "(#{file},1,#{momentId},#{groupType})")
    void addFile(@Param("file")String file,@Param("momentId")Long momentId, @Param("groupType")Long groupType);

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

    /**
     * 根据用户id获取用户的所有动态
     * @param creatorId
     * @return
     */
    @Select("select moment_id, description where creator_id = #{creatorId}")
    List<MomentDTO> listMomentByUserId(long creatorId);
}
