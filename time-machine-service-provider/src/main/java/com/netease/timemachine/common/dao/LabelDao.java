package com.netease.timemachine.common.dao;

import com.netease.timemachine.common.dto.LabelDTO;
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
     * 根据标签id获取标签
     * @param ids
     * @return
     */
    @Select({
            "<script>",
                "select",
                "id, name, user_id",
                "from label",
                "where id in",
                    "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
                    "#{id}",
                    "</foreach>",
            "</script>"
    })
    List<LabelDTO> getLabelsByIds(@Param("ids") List<Long> ids);

    /**
     * 获得历史标签
     * @param userId
     * @param childId
     * @return
     */
    @Select("select name from label where user_id=#{userId} and child_id=#{childId} and type=1 order by gmt_modified limit 5")
    List<String> getHistoryLabels(@Param("userId") Long userId, @Param("childId")Long childId);


    /**
     * 获得家人标签，如果label表中存在则直接获得，如果不存在则先查询user_child_group表再插入label表
     * @param userId
     * @param childId
     * @return
     */

    @Select("select name from label where user_id=#{userId} and child_id=#{childId} and type=2")
    List<String> getFamilyLabelsFromLabel(@Param("userId") Long userId,
                                          @Param("childId")Long childId);

    @Select("select nick_name from user_child_group where child_id=#{childId}")
    List<String> getFamilyLabelsFromUCG(@Param("childId")Long childId);

    @Select("insert into label(name,type,user_id,child_id) values(#{name},2,#{userId},#{childId})")
    void insertFamilyLabel(@Param("userId")Long userId, @Param("childId")Long childId,@Param("name")String name);

    /**
     * 获得推荐标签
     * @param userId
     * @param childId
     * @return
     */
    @Select("select name from label where user_id=#{userId} and child_id=#{childId} and type=3")
    List<String> getRecommendLabels(@Param("userId") Long userId, @Param("childId")Long childId);


    /**
     * 查询插入的标签是否存在
     * @param userId
     * @param childId
     * @param name
     * @return
     */
    @Select("select type from label where user_id=#{userId} and child_id=#{childId} and name=#{name}")
    List<Long> searchLabel(@Param("userId")Long userId, @Param("childId")Long childId, @Param("name")String name);

    /**
     * 如果为用户自己输入的标签，则插入标签表
     * @param userId
     * @param childId
     * @param labelName
     */
    @Insert("insert into label(user_id,child_id,name,type) values(#{userId},#{childId},#{labelName},4)")
    void addLabel(@Param("userId") Long userId, @Param("childId")Long childId,
                  @Param("labelName")String labelName);

    /**
     * 如果该标签为家人或推荐标签，但是该标签没被插入过，则插入历史标签
     * @param userId
     * @param childId
     * @param labelName
     */
    @Insert("insert into label(user_id,child_id,name,type) values(#{userId},#{childId},#{labelName},1)")
    void addHistoryLabel(@Param("userId") Long userId, @Param("childId")Long childId,
                         @Param("labelName")String labelName);

    /**
     * 如果该标签为历史标签，则更新历史标签的时间
     * @param userId
     * @param childId
     * @param labelName
     */
    @Update("update label set gmt_modified=now() where user_id=#{userId} and child_id=#{childId} and name=#{labelName} and type=1")
    void updateLabel(@Param("userId") Long userId, @Param("childId")Long childId,
                     @Param("labelName")String labelName);
}
