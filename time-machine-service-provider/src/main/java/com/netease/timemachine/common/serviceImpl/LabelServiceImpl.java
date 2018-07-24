package com.netease.timemachine.common.serviceImpl;

import com.netease.timemachine.common.dao.LabelDao;
import com.netease.timemachine.common.dto.LabelDTO;
import com.netease.timemachine.common.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/24 下午3:16
 */
@Service
public class LabelServiceImpl implements LabelService{

    @Autowired
    private LabelDao labelDao;

    @Override
    public List<LabelDTO> getLabelsByIds(List<Long> ids) {
        return labelDao.getLabelsByIds(ids);
    }
}
