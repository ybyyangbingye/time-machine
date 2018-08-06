package com.netease.timemachine.moment.dao;

import com.netease.timemachine.moment.meta.Label;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/24 下午3:05
 */
@Mapper
public interface LabelDao {

    /**
     * 获得历史标签
     * @param userId
     * @param childId
     * @return
     */
    @Select("select name from label where user_id=#{userId} and child_id=#{childId} order by gmt_modified desc limit 5")
    List<String> getHistoryLabels(@Param("userId") Long userId, @Param("childId")Long childId);

    /**
     * 从user_child_group表中获得家人标签
     * @param childId
     * @return
     */
    @Select("select nick_name from user_child_group where child_id=#{childId}")
    List<String> getFamilyLabelsFromUCG(@Param("childId")Long childId);

    /**
     * 从recommendlabel获得推荐标签
     * @return
     */
    @Select("select label_name from recommendlabel")
    List<String> getRecommendLabels2();


    @Insert("insert into label(group_id,name,user_id,child_id) values(#{momentId},#{name},#{userId},#{childId})")
    void addLabel(@Param("momentId")Long momentId, @Param("name")String name,
                  @Param("userId")Long userId, @Param("childId")Long childId);
//    /**
//     * 获得家人标签，如果label表中存在则直接获得，如果不存在则先查询user_child_group表再插入label表
//     * @param childId
//     * @return
//     */

//    @Select("select name from label where user_id=#{userId} and child_id=#{childId} and type=2")
//    List<String> getFamilyLabelsFromLabel(@Param("userId") Long userId,
//                                          @Param("childId")Long childId);


//    @Select("insert into label(name,type,user_id,child_id) values(#{name},#{labelType},#{userId},#{childId})")
//    @Options(useGeneratedKeys = true,keyProperty = "labelId")
//    Long insertFamilyLabel(@Param("userId")Long userId, @Param("childId")Long childId,@Param("name")String name);
//    void insertFamilyLabel(Label label);


//    /**
//     * 每次获得推荐标签都要先向数据库插入推荐标签
//     * @param labelName
//     * @param childId
//     * @param userId
//     */
//    @Insert("insert into label(name,type,child_id,user_id) values(#{labelName},3,#{childId},#{userId})")
//    void insertRecommendLabels(@Param("labelName")String labelName, @Param("childId")Long childId, @Param("userId") Long userId);

//    /**
//     * 获得推荐标签
//     * @param userId
//     * @param childId
//     * @return
//     */
//    @Select("select name from label where user_id=#{userId} and child_id=#{childId} and type=3")
//    List<String> getRecommendLabels(@Param("userId") Long userId, @Param("childId")Long childId);

//    /**
//     * 查询插入的标签是否存在
//     * @param userId
//     * @param childId
//     * @param name
//     * @return
//     */
//    @Select("select type from label where user_id=#{userId} and child_id=#{childId} and name=#{name}")
//    List<Long> searchLabel(@Param("userId")Long userId, @Param("childId")Long childId, @Param("name")String name);
//
//    /**
//     * 如果为用户自己输入的标签，则标记为历史标签并插入
//     */
//    @Insert("insert into label(user_id,child_id,name,type) values(#{userId},#{childId},#{labelName},#{labelType})")
//    @Options(useGeneratedKeys = true,keyProperty = "labelId")
//    Long addLabel(@Param("userId") Long userId, @Param("childId")Long childId,
//                  @Param("labelName")String labelName);
//    void addLabel(Label label);

//    /**
//     * 如果该标签为家人或推荐标签，但是该标签没被插入过，则插入历史标签
//     */
//    @Insert("insert into label(user_id,child_id,name,type) values(#{userId},#{childId},#{labelName},#{labelType})")
//    @Options(useGeneratedKeys = true,keyProperty = "labelId")
//    Long addHistoryLabel(@Param("userId") Long userId, @Param("childId")Long childId,
//                        @Param("labelName")String labelName);
//    void addHistoryLabel(Label label);

//    /**
//     * 用户添加过标签后，插入到label_belonged表里并标记为2，代表状态的标签
//     */
//    @Insert("insert into label_belonged(label_id,group_type,group_id) values(#{labelId},2,#{groupId})")
//    void addLabelBelonged(@Param("labelId") Long labelId, @Param("groupId")Long groupId);

//    @Update("update label set group_id=#{groupId} where id=#{labelId}")
//    void updateId(@Param("groupId") Long groupId, @Param("labelId") Long labelId);

//    /**
//     * 如果该标签为历史标签，则更新历史标签的时间
//     * @param userId
//     * @param childId
//     * @param labelName
//     */
//    @Update("update label set gmt_modified=now() where user_id=#{userId} and child_id=#{childId} and name=#{labelName} and type=1")
//    void updateLabel(@Param("userId") Long userId, @Param("childId")Long childId,
//                     @Param("labelName")String labelName);
}
