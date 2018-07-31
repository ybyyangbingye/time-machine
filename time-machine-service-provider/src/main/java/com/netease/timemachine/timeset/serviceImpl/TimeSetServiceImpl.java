package com.netease.timemachine.timeset.serviceImpl;

import com.netease.timemachine.moment.meta.Resource;
import com.netease.timemachine.timeset.dao.TimeSetDao;
import com.netease.timemachine.timeset.dto.TimeSetDTO;
import com.netease.timemachine.timeset.meta.TimeSetByLabel;
import com.netease.timemachine.timeset.service.TimeSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 17:10 2018/7/27
 **/
@Service
public class TimeSetServiceImpl implements TimeSetService {

    private static final Integer MAX_PICS = 12;

    @Autowired
    private TimeSetDao timeSetDao;

    @Override
    public List<String> searchLastMonthByViews(Long childId) {
        List<String> listByTime = timeSetDao.searchLastMonthByViews(childId);
        if(!CollectionUtils.isEmpty(listByTime) && listByTime.size() >= MAX_PICS){
            return listByTime.subList(0, MAX_PICS);
        }
        return null;
    }

    @Override
    public Map<String, List<String>> searchLastMonthByLabels(Long childId) {
        /**获取所有的满足条件的标签name,groupId,groupType：前一个月，孩子id*/
        List<TimeSetByLabel> timeSetByLabelList = timeSetDao.searchLastMonthByLabels(childId);
        Map<String, List<String>> res = null;
        if(!CollectionUtils.isEmpty(timeSetByLabelList)) {
            res = new HashMap<>();
            Map<String, List<Resource>> listMap = new HashMap<>();
            /**获取每种标签中，关联的图片数量*/
            for (TimeSetByLabel label : timeSetByLabelList) {
                List<Resource> resourceList = timeSetDao.searchByGroupIdAndType(label.getGroupId(), label.getGroupType());
                String name = label.getName();
                List<Resource> resources = listMap.get(name);
                if (listMap.containsKey(name)) {
                    resources.addAll(resourceList);
                    listMap.put(name, resources);
                } else {
                    listMap.put(name, resourceList);
                }
            }

            /**对于不超过12张的删除*/
            Iterator<Map.Entry<String, List<Resource>>> it = listMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, List<Resource>> entry = it.next();
                if (entry.getValue().size() < MAX_PICS) {
                    it.remove();
                }
            }
            /**筛选相关照片量最多的三个标签*/
            List<Map.Entry<String, List<Resource>>> list = new ArrayList<>(listMap.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<String, List<Resource>>>() {
                @Override
                public int compare(Map.Entry<String, List<Resource>> o1, Map.Entry<String, List<Resource>> o2) {
                    return o2.getValue().size() - o1.getValue().size();
                }
            });
            int size = list.size() > 3 ? 3 : list.size();
            for (int i = 0; i < size; i++) {
                List<Resource> resources = list.get(i).getValue();
                Collections.sort(resources);
                resources = resources.subList(0, MAX_PICS);
                List<String> stringList = new ArrayList<>();
                for (int j = 0; j < resources.size(); j++) {
                    stringList.add(resources.get(j).getResource_obj());
                }
                res.put(list.get(i).getKey(), stringList);
            }
        }
        return res;
    }

    @Override
    public void addTimeSetFile(String file, Long setId) {
        timeSetDao.addTimeSetFile(file, setId);
    }

    @Override
    public void addTimeSet(TimeSetDTO timeSetDTO) {
        timeSetDao.addTimeSet(timeSetDTO);
    }

    @Override
    public boolean isExist(String setName) {
        return timeSetDao.isExist(setName);
    }
}
