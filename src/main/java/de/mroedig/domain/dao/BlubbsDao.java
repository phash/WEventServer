package de.mroedig.domain.dao;
import org.springframework.data.repository.*;
import de.mroedig.domain.entity.Blubbs;


public interface BlubbsDao extends Repository<Blubbs, Long> {
    void persist(Blubbs b);
}
