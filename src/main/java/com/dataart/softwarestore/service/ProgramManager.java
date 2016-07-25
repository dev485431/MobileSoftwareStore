package com.dataart.softwarestore.service;

import com.dataart.softwarestore.model.domain.Program;

public interface ProgramManager {

    void addProgram(Program program);

    boolean programNameExists(String name);

    Program getProgramById(Integer id);

    void removeProgram(Integer id);

}
