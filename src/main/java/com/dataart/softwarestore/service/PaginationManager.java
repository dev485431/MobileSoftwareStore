package com.dataart.softwarestore.service;

import com.dataart.softwarestore.model.dto.ProgramBasicInfoDto;

import java.util.List;

public interface PaginationManager {

    List<ProgramBasicInfoDto> getPage(Integer pageNum, Integer categoryId, Integer itemsPerPage);

}
