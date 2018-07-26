package com.netease.timemachine.define;

/**
 * 视频文件等资源所属的分组
 *
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/24 上午9:53
 */
public enum GroupTypeEnum {

    // 里程碑
    MILESTONE(1),
    // 朋友圈
    FRIEND_CIRCLE(2);

    int type;
    GroupTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
