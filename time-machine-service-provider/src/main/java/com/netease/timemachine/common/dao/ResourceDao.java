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

    /**
     * 根据组id和组类型获取对应的资源
     * @param groupId
     * @param groupType
     * @return
     */
    @Select("select id, resource_obj, resource_type, group_id, group_type, gmt_create, gmt_modified from resource where group_id = #{groupId} and group_type = #{groupType}")
    List<ResourceDTO> getResourceByGroupIdAndGroupType(@Param("groupId") long groupId, @Param("groupType") int groupType);

    /**
     * 根据组类型和组id删除对应的资源
     *
     * @param groupId
     * @param groupType
     * @return
     */
    @Delete("delete from resource where group_id = #{groupId} and group_type = #{groupType}")
    boolean deleteResourceByGroupIdAndGroupType(@Param("groupId") long groupId, @Param("groupType") long groupType);

    /**
     * 根据id删除资源
     * @param id
     * @return
     */
    @Delete("delete from resource where id = #{id}")
    boolean deleteResourceById(@Param("id") long id);

    @Select("select r.id, r.resource_obj, r.resource_type, r.group_id, r.group_type, r.gmt_create, r.gmt_modified " +
            "from resource r, milestone_event me where me.milestone_id = #{milestoneId} and me.id = r.group_id limit 1")
    ResourceDTO getResourceByMilestoneId(long milestoneId);

    /**
     * 更新浏览量
     * @param resourceObj
     * @return
     */
    @Update("update resource set views = views + 1 where resource_obj = #{resourceObj}")
    boolean updateViewsByResourceObj(@Param("resourceObj") String resourceObj);

    /**
     * 根据组类型和组id集合查询数据
     * @param groupIds
     * @param goupType
     * @return
     */
    @Select({
            "<script>",
            "select",
            "id, resource_obj, grout_id, group_type",
            "from resource",
            "where group_type = #{groupType} and group_id in",
            "<foreach collection='groupIds' item='groupId' open='(' separator=',' close=')'>",
            "#{groupId}",
            "</foreach>",
            "</script>"
    })
    List<ResourceDTO> listByGroupIdAndGroupType(@Param("groupIds") List<Long> groupIds, @Param("groupType") int goupType);

    /**
     * 更新浏览量
     * @param groupId
     * @param resourceObj
     * @return
     */
    @Update("update resource set views = views + 1 where group_id = #{groupId} and resource_obj = #{resourceObj}")
    boolean updateViewsByGroupIdAndResourceId(@Param("groupId") long groupId, @Param("resourceObj") String resourceObj);
}
