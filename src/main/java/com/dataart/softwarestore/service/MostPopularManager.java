package com.dataart.softwarestore.service;

import com.dataart.softwarestore.model.domain.Program;

import java.util.List;

public interface MostPopularManager {

    List<Program> getTopPrograms(int limit, QueryResultsOrder downloadOrder, QueryResultsOrder timeUploadedOrder);

}
