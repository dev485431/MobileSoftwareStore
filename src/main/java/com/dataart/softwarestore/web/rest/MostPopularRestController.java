package com.dataart.softwarestore.web.rest;

import com.dataart.softwarestore.model.domain.Program;
import com.dataart.softwarestore.service.MostPopularManager;
import com.dataart.softwarestore.service.QueryResultsOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/top")
public class MostPopularRestController {


    @Value("${top.programs.quantity.to.query}")
    private int topProgramsQuantityToQuery;
    @Autowired
    private MostPopularManager mostPopularManager;

    @RequestMapping(value = "get", method = RequestMethod.GET)
    private List<Program> getTopPrograms() {
        List<Program> topPrograms = mostPopularManager.getTopPrograms(topProgramsQuantityToQuery, QueryResultsOrder.DESCENDING, QueryResultsOrder.DESCENDING);
        return topPrograms;
    }


}
