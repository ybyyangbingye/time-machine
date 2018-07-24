package com.netease.timemachine.common.dao;

import com.netease.timemachine.common.dto.LabelDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
}
