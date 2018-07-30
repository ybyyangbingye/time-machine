package com.netease.timemachine.moment.serviceImpl;

import com.netease.timemachine.moment.dao.GivealikeDao;
import com.netease.timemachine.moment.dto.GivealikeDTO;
import com.netease.timemachine.moment.meta.Givealike;
import com.netease.timemachine.moment.service.GivealikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



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
        Long momentId=givealikeDTO.getMomentId();
        Long childId=givealikeDao.getChildId(momentId);
        Long userId=givealikeDTO.getUserId();
        String nickname= givealikeDao.getNickname(childId,userId);
        return nickname;
    }

    @Override
    public void addGivealike(GivealikeDTO givealikeDTO) {
        Long momentId=givealikeDTO.getMomentId();
        Long userId=givealikeDTO.getUserId();
        String nickname=getNickname(givealikeDTO);
        givealikeDao.insertGivealike(new Givealike(userId,momentId,nickname));
    }

    @Override
    public void deletealike(GivealikeDTO givealikeDTO) {
        Long momentId=givealikeDTO.getMomentId();
        Long userId=givealikeDTO.getUserId();
        givealikeDao.deleteGivealike(userId,momentId);
    }

    @Override
    public boolean isGivealike(GivealikeDTO givealikeDTO) {
        Long momentId=givealikeDTO.getMomentId();
        Long userId=givealikeDTO.getUserId();
        if(givealikeDao.isGivealike(userId,momentId)!=0) {
            return true;
        }
        else {
            return false;
        }
    }
}
