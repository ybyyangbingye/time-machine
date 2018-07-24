package com.netease.timemachine.common.service;

import com.netease.timemachine.common.dto.ResourceDTO;

import java.util.List;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午6:50
 */
public interface ResourceService {
    /**
     * 添加图片、视频等资源
     * @param resourceDTO
     * @return
     */
    boolean addResource(ResourceDTO resourceDTO);

    /**
     * 根据资源id和所属分组查询资源
     * @param groupId
     * @param groupType
     * @return
     */
    List<ResourceDTO> getResourceByGroupIdAndGroupType(long groupId, int groupType);

    /**
     * 根据资源组类型和组id删除资源
     * @param groupId
     * @param groupType
     * @return
     */
    boolean deleteResourceByGroupIdAndGroupType(long groupId, int groupType);

    /**
     * 根据id删除资源
     * @param id
     * @return
     */
    boolean deleteResourceById(long id);
}
