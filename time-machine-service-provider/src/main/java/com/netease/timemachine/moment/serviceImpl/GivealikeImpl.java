package com.netease.timemachine.moment.serviceImpl;

import com.netease.timemachine.moment.dao.GivealikeDao;
import com.netease.timemachine.moment.dto.GivealikeDTO;
import com.netease.timemachine.moment.meta.Givealike;
import com.netease.timemachine.moment.service.GivealikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Author: ZLS
 * @Description:
 * @Date: 2018/7/25 20:13
 */
@Service
public class GivealikeImpl implements GivealikeService {

    @Resource
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
    public Long addGivealike(GivealikeDTO givealikeDTO) {
        long groupId=givealikeDTO.getGroupId();
        long userId=givealikeDTO.getUserId();
        String nickname=getNickname(givealikeDTO);
        Givealike givealike = new Givealike(userId,groupId,nickname);
        givealikeDao.insertGivealike(givealike);
        return givealike.getLikeId();
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
    public Long getLikedUser(Long groupId) {
        return givealikeDao.getLikedUser(groupId);
    }

    @Override
    public int getLoverCountByMomentId(Long groupId) {
        return givealikeDao.getLoverCountByMomentId(groupId);
    }
}
