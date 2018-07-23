package com.netease.timemachine.common.serviceImpl;

import com.netease.timemachine.common.dao.ResourceDao;
import com.netease.timemachine.common.dto.ResourceDTO;
import com.netease.timemachine.common.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午7:08
 */
@Service
public class ResourceServiceImpl implements ResourceService{

    @Autowired
    private ResourceDao resourceDao;

    @Override
    public boolean addResource(ResourceDTO resourceDTO) {
        return resourceDao.addResource(resourceDTO);
    }
}
