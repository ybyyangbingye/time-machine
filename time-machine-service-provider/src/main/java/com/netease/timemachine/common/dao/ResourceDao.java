package com.netease.timemachine.common.dao;

import com.netease.timemachine.common.dto.ResourceDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午7:02
 */
@Mapper
public interface ResourceDao {

    @Insert("insert into resource(resource_obj, resource_type, group_id, group_type, gmt_create)" +
            "values(#{resourceObj}, #{resourceType}, #{groupId}, #{groupType}, #{gmtCreate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    boolean addResource(ResourceDTO resourceDTO);

    @Select("select id, resource_obj, resource_type, group_id, group_type, gmt_create, gmt_modified from resource where group_id = #{groupId} and group_type = #{groupType}")
    List<ResourceDTO> getResourceByGroupIdAndGroupType(@Param("groupId") long groupId, @Param("groupType") int groupType);


}
