package com.netease.timemachine.common.dao;

import com.netease.timemachine.common.dto.LabelBelongedDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/24 下午2:19
 */
@Mapper
public interface LabelBelongedDao {

    @Insert("insert into label_belonged(label_id, group_type, group_id, gmt_create)" +
            "values(#{labelId}, #{groupType}, #{groupId}, #{gmtCreate}")
    boolean addLabelBelonged(LabelBelongedDTO labelBelongedDTO);

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
}
