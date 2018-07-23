package com.netease.timemachine.common.dao;

import com.netease.timemachine.common.dto.ResourceDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午7:02
 */
@Mapper
public interface ResourceDao {

    @Insert("insert into resource(resource_obj, type, parent_id, gmt_create)" +
            "values(#{resourceObj}, #{type}, #{parentId}, #{gmtCreate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    boolean addResource(ResourceDTO resourceDTO);
}
