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

    List<Publication> listPublicationByUserId(Long id);

    List<Publication> listLatestPub(int num);

    int addNewPublication(Publication publication);

    int editPublication(Publication publication);

    int deletePublication(Long id);

    Publication getPublicationById(Long id);
}
