package com.dataart.softwarestore.web;

import com.dataart.softwarestore.service.CategoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    private static final String MAIN_PAGE = "index";
    @Autowired
    private CategoryManager categoryManager;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String getMainPage(Model model) {
        model.addAttribute("allCategories", categoryManager.getAllCategories());
        return MAIN_PAGE;
    }

}
