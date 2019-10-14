package de.mroedig.domain.dao.impl;

import de.mroedig.domain.dao.BlubbsDao;
import de.mroedig.domain.entity.Blubbs;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BlubbsDaoImpl implements BlubbsDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void persist(Blubbs b) {
        em.persist(b);
    }
}

