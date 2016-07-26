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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;

@Controller
public class ProgramController {

    private static final Logger LOG = Logger.getLogger(ProgramController.class);
    private static final String PROGRAM_SUBMIT_PAGE = "submit";
    private static final String PROGRAM_DETAILS_PAGE = "details";
    private static final Long INITIAL_DOWNLOADS = 0L;
    private static final int SIZE_DIVIDER = 1024;

    private ProgramManager programManager;
    private CategoryManager categoryManager;
    private ProgramFormValidator programFormValidator;
    @Value("${uploaded.file.max.size.bytes}")
    private Long uploadedFileMaxSizeBytes;

    @Autowired
    public ProgramController(ProgramManager programManager, CategoryManager categoryManager, ProgramFormValidator programFormValidator) {
        this.programManager = programManager;
        this.categoryManager = categoryManager;
        this.programFormValidator = programFormValidator;
    }

    @InitBinder("programForm")
    private void initProgramFormValidation(WebDataBinder binder) {
        binder.addValidators(programFormValidator);
    }

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String getAddProgramForm(Model model, HttpSession session) {
        LOG.debug("Getting program submit form");
        model.addAttribute("programForm", new ProgramForm());
        model.addAttribute("allCategories", categoryManager.getAllCategories());
        model.addAttribute("maxFileSizeKb", uploadedFileMaxSizeBytes / SIZE_DIVIDER);
        return PROGRAM_SUBMIT_PAGE;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submitAddProgramForm(Model model, @ModelAttribute("programForm") @Valid ProgramForm programForm, BindingResult result, RedirectAttributes redirect) {
        model.addAttribute("allCategories", categoryManager.getAllCategories());
        model.addAttribute("maxFileSizeKb", uploadedFileMaxSizeBytes / SIZE_DIVIDER);
        if (result.hasErrors()) {
            return PROGRAM_SUBMIT_PAGE;
        }

        // INTERACTION WITH ZIP FILE CONTENT NEEDED

        LOG.debug("Adding new program: " + programForm.toString());
        Category category = categoryManager.getCategoryById(programForm.getCategoryId());
        Statistics statistics = new Statistics(LocalDateTime.now(), INITIAL_DOWNLOADS);
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


}
