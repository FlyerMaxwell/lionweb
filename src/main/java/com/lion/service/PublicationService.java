package com.lion.service;

import com.lion.entity.Publication;
import com.sun.javafx.logging.PulseLogger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author DJ
 * @date 2017/10/21.
 */
public interface PublicationService {
    Publication listAllPublication();

    int addNewPublication(Publication publication);
}
