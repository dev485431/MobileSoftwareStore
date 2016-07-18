package com.dataart.softwarestore.service.hibernate;

import com.dataart.softwarestore.model.domain.Program;
import com.dataart.softwarestore.service.MostPopularManager;
import com.dataart.softwarestore.service.QueryResultsOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HibernateMostPopularManager implements MostPopularManager {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Program> getTopPrograms(int limit, QueryResultsOrder downloadOrder, QueryResultsOrder timeUploadedOrder) {
        String query = "from Program p order by p.statistics.downloads " + downloadOrder.value() + ", p.statistics.timeUploaded " + timeUploadedOrder.value();
        return session().createQuery(query)
                .setMaxResults(limit)
                .setCacheable(true).list();
    }
}


