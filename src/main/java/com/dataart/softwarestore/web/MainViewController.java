package com.dataart.softwarestore.web;

import com.dataart.softwarestore.service.CategoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainViewController {

    private static final String MAIN_PAGE = "index";
    private static final String PROGRAM_DETAILS_PAGE = "details";
    private static final String PROGRAM_SUBMIT_PAGE = "submit";
    @Autowired
    private CategoryManager categoryManager;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String getMainPage(Model model) {
        model.addAttribute("allCategories", categoryManager.getAllCategories());
        return MAIN_PAGE;
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    private String getProgramDetailsPage() {
        return PROGRAM_DETAILS_PAGE;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    private String getProgramSubmitPage() {
        return PROGRAM_SUBMIT_PAGE;
    }

}
