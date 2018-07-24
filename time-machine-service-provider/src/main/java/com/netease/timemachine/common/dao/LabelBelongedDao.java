package com.netease.timemachine.common.dao;

import com.netease.timemachine.common.dto.LabelBelongedDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/24 下午2:19
 */
@Mapper
public interface LabelBelongedDao {

    @Insert("insert into label_belonged(label_id, group_type, group_id, gmt_create)" +
            "values(#{labelId}, #{groupType}, #{groupId}, #{gmtCreate})")
    boolean addLabelBelonged(LabelBelongedDTO labelBelongedDTO);

    /**
     * 获取动态上的标签集合
     * @param groupType
     * @param groupId
     * @return
     */
    @Select("select id, label_id, group_type, group_id, gmt_create, gmt_modified from label_belonged where group_type = #{groupType} and group_id = #{groupId}")
    List<LabelBelongedDTO> getByGroupTypeAndGroupId(@Param("groupType") int groupType, @Param("groupId") long groupId);

    /**
     * 删除某个动态下的所有标签
     * @param groupType
     * @param groupId
     * @return
     */
    @Delete("delete from label_belonged where group_type = #{groupType} and group_id = #{groupId}")
    boolean deleteByGroupTypeAndGroupId(@Param("groupType") int groupType, @Param("groupId") long groupId);

    /**
     * 删除某个动态下的某个标签
     * @param groupType
     * @param groupId
     * @param labelId
     * @return
     */
    @Delete("delete from label_belonged where group_type = #{groupType} and group_id = #{groupId}")
    boolean deleteByGroupTypeAndGroupIdAndLabelId(@Param("groupType") int groupType, @Param("groupId") long groupId, @Param("labelId") long labelId);

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    @Delete("delete from label_belonged where id = #{id}")
    boolean deleteLabelBelongedById(long id);
}
