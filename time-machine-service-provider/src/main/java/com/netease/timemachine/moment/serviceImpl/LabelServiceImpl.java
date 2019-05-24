package com.netease.timemachine.moment.serviceImpl;

import com.netease.timemachine.moment.dao.LabelDao;
import com.netease.timemachine.moment.service.LabelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/24 下午3:16
 */
@Service
public class LabelServiceImpl implements LabelService{

    //private static String [] recommendLabels = {"过生日","可爱","写真"};

    @Resource
    private LabelDao labelDao;

    /**
     * 获得历史标签
     * @param userId
     * @param childId
     * @return
     */
    @Override
    public List<String> getHistoryLabels(Long userId, Long childId) {
        return labelDao.getHistoryLabels(userId, childId);
    }

    /**
     * 获得家人标签
     * @param userId
     * @param childId
     * @return
     */
    @Override
    public List<String> getFamilyLabels(Long userId, Long childId) {
        List<String> res = labelDao.getFamilyLabelsFromUCG(childId);
//        List<String> res1 = labelDao.getFamilyLabelsFromLabel(userId, childId);
//        if(res1.size() == res2.size()) {
//            return res1;
//        }
//        for(String s : res2) {
//            if(!res1.contains(s)) {
//                Label label = new Label();
//                label.setUserId(userId);
//                label.setChildId(childId);
//                label.setLabelName(s);
//                label.setLabelType(2L);
//                labelDao.insertFamilyLabel(label);
//                labelDao.addLabelBelonged(label.getLabelId(),);
//            }
//        }
        return res;
    }

    /**
     * 获得推荐标签
     * @param userId
     * @param childId
     * @return
     */
    @Override
    public List<String> getRecommendLabels(Long userId, Long childId) {
        List<String> res = labelDao.getRecommendLabels2();
        return res;
//        List<String> res = labelDao.getRecommendLabels(userId, childId);
//        if(CollectionUtils.isEmpty(res)) {
//            for(String s : recommendLabels) {
//                labelDao.insertRecommendLabels(s,childId,userId);
//            }
//        }
//        return labelDao.getRecommendLabels(userId, childId);
    }

    /**
     * 添加标签
     * @param userId
     * @param childId
     * @param labels
     */
    @Override
    public void addLabels(Long userId, Long childId, Long momentId, List<String> labels) {
        for(String s : labels) {
            labelDao.addLabel(momentId,s,userId,childId);
        }
//        List<String> names = getFamilyLabels(userId, childId);
//        for(String s : labels) {
//            List<Long> type = labelDao.searchLabel(userId,childId,s);
//            if(type.size() == 0) {
//                Label label = new Label();
//                label.setUserId(userId);
//                label.setChildId(childId);
//                label.setLabelName(s);
//                if(names.contains(s)) {
//                    // 插入不存在的家人标签
//                    label.setLabelType(2L);
//                    labelDao.insertFamilyLabel(label);
//                }
//                else {
//                    // 插入用户自定义标签
//                    label.setLabelType(1L);
//                    labelDao.addLabel(label);
//                }
//                //labelDao.addLabelBelonged(label.getLabelId(), momentId);
//                labelDao.updateId(momentId, label.getLabelId());
//            }
//            // 插入的是历史标签，即已经存在的推荐标签或家人标签
//            else if(type.size() == 1){
//                Label label = new Label();
//                label.setUserId(userId);
//                label.setChildId(childId);
//                label.setLabelName(s);
//                label.setLabelType(1L);
//                labelDao.addHistoryLabel(label);
//                //labelDao.addLabelBelonged(label.getLabelId(), momentId);
//                labelDao.updateId(momentId, label.getLabelId());
//            }
//            // type.size() == 2 则更新已经插入的历史标签
//            else {
//                labelDao.updateLabel(userId,childId,s);
//            }
//        }
    }
}
