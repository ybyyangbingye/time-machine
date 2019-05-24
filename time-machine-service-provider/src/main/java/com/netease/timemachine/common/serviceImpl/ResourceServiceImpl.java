package com.netease.timemachine.common.serviceImpl;

import com.netease.timemachine.common.dao.ResourceDao;
import com.netease.timemachine.common.dto.ResourceDTO;
import com.netease.timemachine.common.service.ResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午7:08
 */
@Service
public class ResourceServiceImpl implements ResourceService{

    @Resource
    private ResourceDao resourceDao;

    @Override
    public boolean addResource(ResourceDTO resourceDTO) {
        return resourceDao.addResource(resourceDTO);
    }

    @Override
    public List<ResourceDTO> getResourceByGroupIdAndGroupType(long groupId, int groupType) {
        return resourceDao.getResourceByGroupIdAndGroupType(groupId, groupType);
    }

    @Override
    public boolean deleteResourceByGroupIdAndGroupType(long groupId, int groupType) {
        return resourceDao.deleteResourceByGroupIdAndGroupType(groupId, groupType);
    }

    @Override
    public boolean deleteResourceById(long id) {
        return resourceDao.deleteResourceById(id);
    }

    @Override
    public ResourceDTO getResourceByMilestoneId(long milestoneId) {
        return resourceDao.getResourceByMilestoneId(milestoneId);
    }

    @Override
    public boolean updateViewsByResourceObj(String resourceObj) {
        return resourceDao.updateViewsByResourceObj(resourceObj);
    }

    @Override
    public List<ResourceDTO> listByGroupIdAndGroupType(List<Long> groupIds, int groupType) {
        return resourceDao.listByGroupIdAndGroupType(groupIds, groupType);
    }

    @Override
    public boolean updateViewsByGroupIdAndResourceId(long groupId, String resourceObj) {
        return resourceDao.updateViewsByGroupIdAndResourceId(groupId, resourceObj);
    }
}
