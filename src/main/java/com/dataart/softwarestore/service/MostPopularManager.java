package com.dataart.softwarestore.service;

import com.dataart.softwarestore.model.domain.Program;

import java.util.List;

public interface MostPopularManager {

    List<Program> getTopPrograms(Integer limit, QueryResultsOrder downloadOrder, QueryResultsOrder timeUploadedOrder);

}
