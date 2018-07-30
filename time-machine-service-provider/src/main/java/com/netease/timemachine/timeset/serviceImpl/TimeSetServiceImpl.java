package com.netease.timemachine.timeset.serviceImpl;

import com.netease.timemachine.timeset.dao.TimeSetDao;
import com.netease.timemachine.timeset.service.TimeSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 17:10 2018/7/27
 **/
@Service
public class TimeSetServiceImpl implements TimeSetService {
    @Autowired
    private TimeSetDao timeSetDao;

    @Override
    public List<HashMap> searchLastMonthByViews(Long childId) {
        return timeSetDao.searchLastMonthByViews(childId);
    }

}
