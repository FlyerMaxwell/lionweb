package com.lion.service;

import com.lion.entity.Publication;
import com.sun.javafx.logging.PulseLogger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author DJ
 * @date 2017/10/21.
 */
public interface PublicationService {
    List<Publication> listAllPublication();

    int addNewPublication(Publication publication);
}
