package com.dataart.softwarestore.web;

import com.dataart.softwarestore.model.domain.Program;
import com.dataart.softwarestore.service.ProgramsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/programs")
public class ProgramsController {

    @Autowired
    private ProgramsManager programsManager;

    @RequestMapping(value = "page/{pageNum:[\\d]+}", method = RequestMethod.GET)
    private List<Program> getPage(@PathVariable("pageNum") Integer pageNum, @RequestParam(value = "categoryId") Integer categoryId, @RequestParam(value = "itemsPerPage") Integer itemsPerPage) {
        return programsManager.getPage(pageNum, categoryId, itemsPerPage);
    }

}
