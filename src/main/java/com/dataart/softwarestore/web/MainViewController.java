package com.dataart.softwarestore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainViewController {

    private static final String MAIN_PAGE = "index";
    private static final String LOGIN_PAGE = "login";
    private static final String DETAILS_PAGE = "details";
    private static final String SUBMIT_PAGE = "submit";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String getMainPage() {
        return MAIN_PAGE;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    private String getLoginPage() {
        return LOGIN_PAGE;
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    private String getDetailsPage() {
        return DETAILS_PAGE;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    private String getSubmitPage() {
        return SUBMIT_PAGE;
    }

}
