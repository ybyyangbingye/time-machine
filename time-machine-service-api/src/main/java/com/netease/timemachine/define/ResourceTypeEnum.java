package com.netease.timemachine.define;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/24 上午10:04
 */
public enum ResourceTypeEnum {
    //图片
    PICTURE(1),
    // 视频
    VIDEO(2);

    private int type;

    ResourceTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
