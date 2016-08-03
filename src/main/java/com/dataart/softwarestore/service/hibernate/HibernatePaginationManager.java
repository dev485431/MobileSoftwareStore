package com.dataart.softwarestore.service.hibernate;

import com.dataart.softwarestore.model.domain.Program;
import com.dataart.softwarestore.model.dto.ProgramBasicInfoDto;
import com.dataart.softwarestore.service.PaginationManager;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HibernatePaginationManager implements PaginationManager {

    private static final int DECREMENT_BY_ONE = 1;
    @Autowired
    private SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<ProgramBasicInfoDto> getPage(Integer pageNum, Integer categoryId, Integer itemsPerPage) {
        Integer firstResult = pageNum * itemsPerPage;
        Criteria criteria = session().createCriteria(Program.class);
        List<Program> programs = criteria.add(Restrictions.eq("category.id", categoryId))
                .setCacheable(true)
                .setFirstResult(firstResult)
                .setMaxResults(itemsPerPage).list();

        programs.stream().forEach(program -> {
            Hibernate.initialize(program.getCategory());
            Hibernate.initialize(program.getStatistics());
        });
        return programs.stream().map(program -> new ProgramBasicInfoDto(program.getId(), program.getName(), program
                .getDescription(), program.getImg128(), program.getImg512(), program.getCategory().getName(), program
                .getStatistics().getDownloads()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getMaxPageForCategory(Integer categoryId, Integer itemsPerPage) {
        Long programsInCategory = (Long) session().createCriteria(Program.class)
                .add(Restrictions.eq("category.id", categoryId))
                .setProjection(Projections.rowCount()).uniqueResult();
        return (int) Math.ceil((double) programsInCategory / itemsPerPage - DECREMENT_BY_ONE);
    }

}
