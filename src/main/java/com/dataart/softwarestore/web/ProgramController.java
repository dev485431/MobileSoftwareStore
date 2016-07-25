package com.dataart.softwarestore.web;

import com.dataart.softwarestore.model.domain.Category;
import com.dataart.softwarestore.model.domain.Program;
import com.dataart.softwarestore.model.domain.Statistics;
import com.dataart.softwarestore.model.dto.ProgramForm;
import com.dataart.softwarestore.service.CategoryManager;
import com.dataart.softwarestore.service.ProgramManager;
import com.dataart.softwarestore.validation.ProgramFormValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

@Controller
public class ProgramController {

    private static final Logger LOG = Logger.getLogger(ProgramController.class);
    private static final String PROGRAM_SUBMIT_PAGE = "submit";
    private static final String PROGRAM_DETAILS_PAGE = "details";
    private static final String UPLOAD_DIR = "/uploads/";

    @Autowired
    private ProgramManager programManager;
    @Autowired
    private CategoryManager categoryManager;
    @Autowired
    private ProgramFormValidator programFormValidator;
    @Autowired
    private HttpServletRequest request;

    @InitBinder("programForm")
    private void initProgramFormValidation(WebDataBinder binder) {
        binder.addValidators(programFormValidator);
    }

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String getAddProgramForm(Model model, HttpSession session) {
        LOG.debug("Getting program submit form");
        model.addAttribute("programForm", new ProgramForm());
        session.setAttribute("allCategories", categoryManager.getAllCategories());
        return PROGRAM_SUBMIT_PAGE;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submitAddProgramForm(Model model, @ModelAttribute("programForm") @Valid ProgramForm programForm, BindingResult result, RedirectAttributes redirect) throws IOException {
        if (result.hasErrors()) {
            return PROGRAM_SUBMIT_PAGE;
        }
        // validation of uploaded file
        uploadFile(programForm.getFile());

        LOG.debug("Adding new program: " + programForm.toString());
        Category category = categoryManager.getCategoryById(programForm.getCategoryId());
        Statistics statistics = new Statistics(LocalDateTime.now(), 0L);
        // String name, String description, String filename, byte[] data, Category category, Statistics statistics, Map<Integer, Image> images
        Program addedProgram = new Program(programForm.getName(), programForm.getDescription(), programForm.getFile().getOriginalFilename(),
                programForm.getFile().getBytes(), category, statistics, new HashMap<>());
        programManager.addProgram(addedProgram);
        return PROGRAM_SUBMIT_PAGE;
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public String getProgramDetailsPage() {
        return PROGRAM_DETAILS_PAGE;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String removeProgram(@RequestParam("id") Integer id) {
        programManager.removeProgram(id);
        return "/";
    }

    private String uploadFile(CommonsMultipartFile file) throws IOException {
        String realPathToUploads = request.getServletContext().getRealPath(UPLOAD_DIR);
        if (!new File(realPathToUploads).exists()) {
            new File(realPathToUploads).mkdir();
        }
        LOG.debug("Uploading file to: " + realPathToUploads);
        String newPath = realPathToUploads + file.getOriginalFilename();
        file.transferTo(new File(newPath));
        return newPath;
    }

}
