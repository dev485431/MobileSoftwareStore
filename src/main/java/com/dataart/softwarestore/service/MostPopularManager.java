package com.dataart.softwarestore.service;

import com.dataart.softwarestore.model.dto.ProgramBasicInfoDto;

import java.util.List;

public interface MostPopularManager {

    List<ProgramBasicInfoDto> getTopPrograms(Integer limit, QueryResultsOrder downloadOrder, QueryResultsOrder timeUploadedOrder);

}
