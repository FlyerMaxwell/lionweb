package com.lion.service;

import com.lion.entity.Label;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DJ
 * @date 2018/1/2.
 */

public interface LabelService {

    List<Label> listAllLabel();

    Label getLabelById(Long id);

    Label getFormer(Long rank);

    Label getLatter(Long rank);

    int addNewLabel(Label label);

    int editLabel(Label label);

    int deleteLabel(Long id);

}
