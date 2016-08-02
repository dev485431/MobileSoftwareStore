package com.dataart.softwarestore.web;

import com.dataart.softwarestore.model.domain.Program;
import com.dataart.softwarestore.model.dto.ProgramBasicInfoDto;
import com.dataart.softwarestore.service.MostPopularManager;
import com.dataart.softwarestore.service.QueryResultsOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/rest/top")
public class MostPopularController {


    @Value("${top.programs.limit.to.query}")
    private int topProgramsLimitToQuery;
    @Autowired
    private MostPopularManager mostPopularManager;

    @RequestMapping(value = "get", method = RequestMethod.GET)
    private List<ProgramBasicInfoDto> getTopPrograms() {
        List<Program> programs = mostPopularManager.getTopPrograms(topProgramsLimitToQuery, QueryResultsOrder
                .DESCENDING, QueryResultsOrder.DESCENDING);
        return programs.stream().map(program -> new ProgramBasicInfoDto(program.getId(), program.getName(), program
                .getDescription(), program.getImg128(), program.getImg512(), program.getCategory().getName(), program
                .getStatistics().getDownloads()))
                .collect(Collectors.toList());
    }

}
