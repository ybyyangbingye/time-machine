package com.netease.timemachine.moment.vo;

import com.netease.timemachine.moment.dto.GivealikeDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/24 11:18
 */
@Data
@NoArgsConstructor
public class MomentVO implements Serializable {

    private static final long serialVersionUID = 1152494284183844731L;

    private Long momentId;

    private Long creatorId;

    private String tile;

    private Long groupType;

    private String nickName;

    private String childAge;

    private String description;

    private String location;

    private Long childId;

    private List<String> files;

    private List<String> labels;

    private List<GivealikeDTO> giveALike;

    private Boolean hasLike;

    private List<CommentVO> comments;

    private Date gmtCreate;

    private Date gmtModified;

}
