package de.mroedig.service.impl;

import de.mroedig.domain.dao.BlubbsDao;
import de.mroedig.domain.entity.Blubbs;
import de.mroedig.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EsServiceImpl implements EsService {

    @Inject
    private BlubbsDao blubbsDao;

    @Override
    public String name() {
        return "Hello World from Spring";
    }

    @Override
    @Transactional
    public void enterBlubbs(Blubbs blubbs) {
        blubbsDao.persist(blubbs);
    }
}
