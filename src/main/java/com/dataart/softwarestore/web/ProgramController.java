package com.dataart.softwarestore.web;

import com.dataart.softwarestore.model.domain.Program;
import com.dataart.softwarestore.model.dto.ProgramForm;
import com.dataart.softwarestore.service.CategoryManager;
import com.dataart.softwarestore.service.ProgramManager;
import com.dataart.softwarestore.validation.ProgramFormValidator;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ProgramController {

    private static final Logger LOG = Logger.getLogger(ProgramController.class);
    private static final String PROGRAM_SUBMIT_PAGE = "submit";
    private static final String PROGRAM_DETAILS_PAGE = "details";

    @Autowired
    private ProgramManager programManager;
    @Autowired
    private CategoryManager categoryManager;
    @Autowired
    private ProgramFormValidator programFormValidator;

    @InitBinder("programForm")
    private void initProgramFormValidation(WebDataBinder binder) {
        binder.setValidator(programFormValidator);
    }

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    private String getAddProgramForm(Model model) {
        model.addAttribute("programForm", new ProgramForm());
        model.addAttribute("allCategories", categoryManager.getAllCategories());
        LOG.debug("Getting program submit form");
        return PROGRAM_SUBMIT_PAGE;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submitAddProgramForm(Model model, @ModelAttribute("programForm") @Valid ProgramForm programForm, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return PROGRAM_SUBMIT_PAGE;
        }
        LOG.debug("Adding new program: " + programForm.toString());
        Program addedProgram = new Program();


//        programManager.addProgram(new Program());
        return PROGRAM_SUBMIT_PAGE;
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    private String getProgramDetailsPage() {
        return PROGRAM_DETAILS_PAGE;
    }

    @ExceptionHandler({MultipartException.class, FileUploadBase.FileSizeLimitExceededException.class})
    public String multipartResolverFileSizeExceeded(Model model) {
//        redirect.addAttribute("error.msg", "Maximum file size exceeded");
        model.addAttribute("error.msg", "Maximum file size exceeded");
        return PROGRAM_DETAILS_PAGE;

    }


}
