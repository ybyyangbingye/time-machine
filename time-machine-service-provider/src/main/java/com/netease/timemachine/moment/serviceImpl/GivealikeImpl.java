package com.netease.timemachine.moment.serviceImpl;

import com.netease.timemachine.moment.dao.GivealikeDao;
import com.netease.timemachine.moment.dto.GivealikeDTO;
import com.netease.timemachine.moment.meta.Givealike;
import com.netease.timemachine.moment.service.GivealikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
        long groupId=givealikeDTO.getGroupId();
        long childId=givealikeDao.getChildId(groupId);
        long userId=givealikeDTO.getUserId();
        String nickname= givealikeDao.getNickname(childId,userId);
        return nickname;
    }

    @Override
    public void addGivealike(GivealikeDTO givealikeDTO) {
        long groupId=givealikeDTO.getGroupId();
        long userId=givealikeDTO.getUserId();
        String nickname=getNickname(givealikeDTO);
        givealikeDao.insertGivealike(new Givealike(userId,groupId,nickname));
    }

    @Override
    public void deletealike(GivealikeDTO givealikeDTO) {
        long groupId=givealikeDTO.getGroupId();
        long userId=givealikeDTO.getUserId();
        givealikeDao.deleteGivealike(userId,groupId);
    }

    @Override
    public List<GivealikeDTO> getAll(Long groupId) {
        return givealikeDao.getAll(groupId);
    }

    @Override
    public boolean isGivealike(Long userId, Long groupId) {
        if(givealikeDao.isGivealike(userId,groupId)!=0) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int getLoverCountByMomentId(Long groupId) {
        return givealikeDao.getLoverCountByMomentId(groupId);
    }
}
