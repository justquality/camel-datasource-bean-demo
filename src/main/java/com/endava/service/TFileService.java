package com.endava.service;

import com.endava.model.TFile;
import org.apache.camel.Body;
import org.apache.camel.Header;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Dmitri Cadea on 03.01.17.
 */
@Service
public class TFileService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(@Header("CamelFileName") String filename, @Body String body) {
        TFile tFile = new TFile(filename, body);
        entityManager.persist(tFile);
    }

}
