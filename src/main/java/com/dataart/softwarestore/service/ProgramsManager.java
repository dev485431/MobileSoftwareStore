package com.dataart.softwarestore.service;

import com.dataart.softwarestore.model.domain.Program;

import java.util.List;

public interface ProgramsManager {

    List<Program> getPage(Integer pageNum, Integer categoryId, Integer itemsPerPage);

}
