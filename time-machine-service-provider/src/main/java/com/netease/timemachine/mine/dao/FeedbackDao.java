package com.netease.timemachine.mine.dao;

import com.netease.timemachine.mine.dto.FeedBackDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午1:59
 */
@Mapper
public interface FeedbackDao {
    @Insert("insert into feedback(content,image_obj,user_id,gmt_create) " +
            "values(#{content},#{imageObj},#{userId},#{gmtCreate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    boolean addFeedback(FeedBackDTO feedBackDTO);
}
