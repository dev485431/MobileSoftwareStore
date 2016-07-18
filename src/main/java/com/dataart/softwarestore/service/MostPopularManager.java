package com.dataart.softwarestore.service;

import java.util.List;

public interface MostPopularManager {

    List getTopPrograms(int limit, QueryResultsOrder downloadOrder, QueryResultsOrder timeUploadedOrder);

}
