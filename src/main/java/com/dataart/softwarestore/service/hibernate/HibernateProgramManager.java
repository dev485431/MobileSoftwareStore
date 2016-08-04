package com.dataart.softwarestore.service.hibernate;

import com.dataart.softwarestore.model.domain.Program;
import com.dataart.softwarestore.model.dto.ProgramDetailsDto;
import com.dataart.softwarestore.service.ProgramManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

@Service
public class HibernateProgramManager implements ProgramManager {

    @Autowired
    private SessionFactory sessionFactory;
    @Value("${program.details.date.format}")
    private String programDetailsDateFormat;

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
        Long count = (Long) session().createQuery("select count(*) from Program where name=:name").setParameter
                ("name", name).uniqueResult();
        return count == 1;
    }

    @Override
    @Transactional(readOnly = true)
    public Program getProgramById(Integer id) {
        return (Program) session().createQuery("from Program where id=:id").setParameter("id", id).uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramDetailsDto getProgramDetailsById(Integer id) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(programDetailsDateFormat);
        Program program = (Program) session().createQuery("from Program where id=:id").setParameter("id", id)
                .uniqueResult();
        return new ProgramDetailsDto(program.getId(), program.getName(), program
                .getDescription(), program.getImg128(), program.getImg512(), program.getCategory().getName(), program
                .getStatistics().getTimeUploaded().format(dateFormat), program.getStatistics().getDownloads());
    }

    @Override
    @Transactional
    public void removeProgram(Integer id) {
        Program program = getProgramById(id);
        session().delete(program);
    }

}
