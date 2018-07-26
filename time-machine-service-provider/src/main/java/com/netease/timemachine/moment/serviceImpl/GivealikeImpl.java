package com.netease.timemachine.moment.serviceImpl;

import com.netease.timemachine.moment.dao.GivealikeDao;
import com.netease.timemachine.moment.dto.GivealikeDTO;
import com.netease.timemachine.moment.meta.Givealike;
import com.netease.timemachine.moment.service.GivealikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @Author: ZLS
 * @Description:
 * @Date: 2018/7/25 20:13
 */
@Service
public class GivealikeImpl implements GivealikeService {

    @Autowired
    private GivealikeDao givealikeDao;

    @Override
    public String getNickname(GivealikeDTO givealikeDTO) {
        long momentId=givealikeDTO.getMomentId();
        long childId=givealikeDao.getChildId(momentId);
        long userId=givealikeDTO.getUserId();
        String nickname= givealikeDao.getNickname(childId,userId);
        return nickname;
    }

    @Override
    public void addGivealike(GivealikeDTO givealikeDTO) {
        long momentId=givealikeDTO.getMomentId();
        long userId=givealikeDTO.getUserId();
        String nickname=getNickname(givealikeDTO);
        Date gmtCreate=givealikeDTO.getGmtCreate();
        givealikeDao.insertGivealike(new Givealike(0,userId,momentId,nickname,gmtCreate));
    }

    @Override
    public void deletealike(GivealikeDTO givealikeDTO) {
        long momentId=givealikeDTO.getMomentId();
        long userId=givealikeDTO.getUserId();
        givealikeDao.deleteGivealike(userId,momentId);
    }
}
