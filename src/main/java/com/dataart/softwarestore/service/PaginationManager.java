package com.dataart.softwarestore.service;

import com.dataart.softwarestore.model.domain.Program;

import java.util.List;

public interface PaginationManager {

    List<Program> getPage(Integer pageNum, Integer categoryId, Integer itemsPerPage);

}
