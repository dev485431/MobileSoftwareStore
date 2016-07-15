package com.dataart.softwarestore.web.rest;

import com.dataart.softwarestore.model.domain.Category;
import com.dataart.softwarestore.service.CategoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/categories")
public class CategoriesRestController {

    @Autowired
    private CategoryManager categoryManager;

    @RequestMapping(value = "get", method = RequestMethod.GET)
    private List<Category> getAllCategories() {
        List<Category> allCategories = categoryManager.getAllCategories();
        return allCategories;
    }

}
