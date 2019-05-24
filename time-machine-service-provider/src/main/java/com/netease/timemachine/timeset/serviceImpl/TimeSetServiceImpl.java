package com.netease.timemachine.timeset.serviceImpl;

import com.netease.timemachine.moment.meta.Label;
import com.netease.timemachine.moment.meta.Resource;
import com.netease.timemachine.timeset.dao.TimeSetDao;
import com.netease.timemachine.timeset.dto.TimeSetDTO;
import com.netease.timemachine.timeset.service.TimeSetService;
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

    @javax.annotation.Resource
    private TimeSetDao timeSetDao;

    @Override
    public List<HashMap> searchLastMonthByViews(Long childId) {
        List<HashMap> listByTime = timeSetDao.searchLastMonthByViews(childId);
        if(!CollectionUtils.isEmpty(listByTime) && listByTime.size() >= MAX_PICS){
            return listByTime.subList(0, MAX_PICS);
        }
        return null;
    }

    @Override
    public Map<String, List<String>> searchLastMonthByLabels(Long childId) {
        /**获取所有的满足条件的标签name,groupId,groupType：前一个月，孩子id*/
        List<Label> timeSetByLabelList = timeSetDao.searchLastMonthByLabels(childId);
        Map<String, List<String>> res = null;
        if(!CollectionUtils.isEmpty(timeSetByLabelList)) {
            res = new HashMap<>();
            Map<String, List<Resource>> listMap = new HashMap<>();
            /**获取每种标签中，关联的图片数量*/
            for (Label label : timeSetByLabelList) {
                List<Resource> resourceList = timeSetDao.searchByGroupId(label.getGroupId());
                String name = label.getLabelName();
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
    public Long addTimeSet(TimeSetDTO timeSetDTO) {
        timeSetDao.addTimeSet(timeSetDTO);
        return timeSetDTO.getSetId();
    }

    @Override
    public boolean isExist(String setName, Long childId) {
        return timeSetDao.isExist(setName, childId);
    }

    @Override
    public String musicRanByTimeSet() {
        return timeSetDao.musicRanByTimeSet();
    }

    @Override
    public void addTimeSetToResource(String picture, Long groupId) {
        timeSetDao.addTimeSetToResource(picture, groupId);
    }

    @Override
    public List<TimeSetDTO> selectTimeSetById(Long childId) {
        return timeSetDao.selectTimeSetById(childId);
    }

    @Override
    public List<String> selectTimeSetResources(Long setId) {
        return timeSetDao.selectTimeSetResources(setId);
    }

    @Override
    public List<TimeSetDTO> selectTimeSetDetail(Long childId) {
        /**去拉取已经生成的时光集*/
        List<TimeSetDTO> timeSetDTOList = timeSetDao.selectTimeSetById(childId);
        List<TimeSetDTO> res = new ArrayList<>(timeSetDTOList.size());
        if(!CollectionUtils.isEmpty(timeSetDTOList)){
            for(TimeSetDTO timeSetDTO : timeSetDTOList){
                List<String> pictures = timeSetDao.selectTimeSetResources(timeSetDTO.getSetId());
                timeSetDTO.setPictures(pictures);
                res.add(timeSetDTO);
            }
        }
        return res;
    }

    @Override
    public TimeSetDTO selectTimeSetBysetId(Long setId) {
        return timeSetDao.selectTimeSetBysetId(setId);
    }
}
