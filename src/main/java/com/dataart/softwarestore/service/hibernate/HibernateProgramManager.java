package com.dataart.softwarestore.service.hibernate;

import com.dataart.softwarestore.model.domain.Program;
import com.dataart.softwarestore.service.ProgramManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HibernateProgramManager implements ProgramManager {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public void addProgram(Program program) {
        session().save(program);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean programNameExists(String name) {
        Long count = (Long) session().createQuery("select count(*) from Program where name=:name").setParameter("name", name).uniqueResult();
        return count == 1;
    }

    @Override
    @Transactional(readOnly = true)
    public Program getProgramById(Integer id) {
        return (Program) session().createQuery("from Program where id=:id").setParameter("id", id).uniqueResult();
    }

    @Override
    @Transactional
    public void removeProgram(Integer id) {
        Program program = getProgramById(id);
        session().delete(program);
    }

}
