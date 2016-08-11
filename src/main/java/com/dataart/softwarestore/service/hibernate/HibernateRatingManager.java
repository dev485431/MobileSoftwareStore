package com.dataart.softwarestore.service.hibernate;

import com.dataart.softwarestore.model.domain.Program;
import com.dataart.softwarestore.model.domain.Rating;
import com.dataart.softwarestore.service.ProgramManager;
import com.dataart.softwarestore.service.RatingManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HibernateRatingManager implements RatingManager {

    private static final Logger LOG = Logger.getLogger(HibernateRatingManager.class);

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private ProgramManager programManager;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public void addRating(Integer programId, Rating rating) {
        Program program = programManager.getProgramById(programId);
        LOG.debug("Loaded program for rating addition: " + program);
        rating.setStatistics(program.getStatistics());
        program.getStatistics().getRatings().add(rating);
        session().save(program);
    }

}
