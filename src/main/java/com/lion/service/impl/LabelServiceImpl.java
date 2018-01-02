package com.lion.service.impl;

import com.lion.dao.ext.LabelDao;
import com.lion.entity.Label;
import com.lion.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DJ
 * @date 2018/1/2.
 */
@Service("LabelService")
public class LabelServiceImpl implements LabelService{

    @Autowired
    LabelDao labelDao;
    public List<Label> listAllLabel() {
        return labelDao.selectAllLabel();
    }

    public Label getLabelById(Long id) {
        return labelDao.selectByPrimaryKey(id);
    }

    public Label getFormer(Long rank) {
        return labelDao.selectFormer(rank);
    }

    public Label getLatter(Long rank) {
        return labelDao.selectLatter(rank);
    }

    public int addNewLabel(Label label) {
        return labelDao.insertSelective(label);
    }

    public int editLabel(Label label) {
        return labelDao.updateByPrimaryKeySelective(label);
    }

    public int deleteLabel(Long id) {
        return labelDao.deleteByPrimaryKey(id);
    }
}
