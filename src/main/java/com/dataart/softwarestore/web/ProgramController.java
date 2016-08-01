package com.dataart.softwarestore.web;

import com.dataart.softwarestore.model.domain.Category;
import com.dataart.softwarestore.model.domain.Statistics;
import com.dataart.softwarestore.model.dto.ProgramForm;
import com.dataart.softwarestore.service.CategoryManager;
import com.dataart.softwarestore.service.ProgramManager;
import com.dataart.softwarestore.utils.FtpTransferHandler;
import com.dataart.softwarestore.utils.ProgramZipFileHandler;
import com.dataart.softwarestore.validation.AfterUploadFilesValidator;
import com.dataart.softwarestore.validation.ProgramFormValidator;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ProgramController {

    private static final Logger LOG = Logger.getLogger(ProgramController.class);
    private static final String PROGRAM_SUBMIT_PAGE = "submit";
    private static final String PROGRAM_DETAILS_PAGE = "details";
    private static final String REDIRECT_TO_SUBMIT_PAGE = "redirect:/submit";
    private static final String MAIN_UPLOAD_DIR = "/temp_uploads/";
    private static final Long INITIAL_DOWNLOADS = 0L;
    private static final int FILE_SIZE_DIVIDER = 1024;

    private HttpServletRequest servletRequest;
    private MessageSourceAccessor websiteMessages;
    private ProgramManager programManager;
    private CategoryManager categoryManager;
    private ProgramFormValidator programFormValidator;
    private AfterUploadFilesValidator afterUploadFilesValidator;
    private ProgramZipFileHandler programZipFileHandler;
    private FtpTransferHandler ftpTransferHandler;
    @Value("${uploaded.file.max.size.bytes}")
    private Long uploadedFileMaxSizeBytes;


    @Autowired
    public ProgramController(HttpServletRequest servletRequest, MessageSourceAccessor websiteMessages, ProgramManager programManager,
                             CategoryManager categoryManager, ProgramFormValidator programFormValidator, AfterUploadFilesValidator afterUploadFilesValidator,
                             ProgramZipFileHandler programZipFileHandler, FtpTransferHandler ftpTransferHandler) {
        this.servletRequest = servletRequest;
        this.websiteMessages = websiteMessages;
        this.programManager = programManager;
        this.categoryManager = categoryManager;
        this.programFormValidator = programFormValidator;
        this.afterUploadFilesValidator = afterUploadFilesValidator;
        this.programZipFileHandler = programZipFileHandler;
        this.ftpTransferHandler = ftpTransferHandler;
    }

    @InitBinder("programForm")
    private void initProgramFormValidation(WebDataBinder binder) {
        binder.addValidators(programFormValidator);
    }

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String getAddProgramForm(Model model) {
        LOG.debug("Getting program submit form");
        model.addAttribute("programForm", new ProgramForm());
        model.addAttribute("allCategories", categoryManager.getAllCategories());
        model.addAttribute("maxFileSizeKb", uploadedFileMaxSizeBytes / FILE_SIZE_DIVIDER);
        return PROGRAM_SUBMIT_PAGE;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submitAddProgramForm(Model model, @ModelAttribute("programForm") @Valid ProgramForm programForm, BindingResult result, RedirectAttributes redirect) {
        model.addAttribute("allCategories", categoryManager.getAllCategories());
        model.addAttribute("maxFileSizeKb", uploadedFileMaxSizeBytes / FILE_SIZE_DIVIDER);
        if (result.hasErrors()) return PROGRAM_SUBMIT_PAGE;

        File uploadedZipFile = null;
        File extractPath = null;
        try {
            File mainUploadDir = new File(servletRequest.getSession().getServletContext().getRealPath(MAIN_UPLOAD_DIR));
            uploadedZipFile = programZipFileHandler.transferFileToDir(programForm.getFile(), mainUploadDir);
            extractPath = new File(FilenameUtils.removeExtension(uploadedZipFile.getAbsolutePath()));
            List<File> extractedFiles = programZipFileHandler.extractZipFile(uploadedZipFile, extractPath);
            if (afterUploadFilesValidator.areThereEmptyFiles(extractedFiles)) {
                LOG.debug("Some extracted files are empty");
                redirect.addFlashAttribute("errorMessage", websiteMessages.getMessage("error.contains.empty.files"));
                return REDIRECT_TO_SUBMIT_PAGE;
            }
            String targetUploadDir = programForm.getName();
            ftpTransferHandler.uploadFiles(extractedFiles, targetUploadDir);
        } catch (IOException e) {
            LOG.error("Error during processing zip file: " + e.getMessage());
            redirect.addFlashAttribute("errorMessage", websiteMessages.getMessage("error.processing.zip"));
            return REDIRECT_TO_SUBMIT_PAGE;
        } finally {
            LOG.debug("Attempting to remove temporary files");
            programZipFileHandler.removeFiles(uploadedZipFile, extractPath);
        }

        LOG.debug("Adding new program: " + programForm.toString());
        Category category = categoryManager.getCategoryById(programForm.getCategoryId());
        Statistics statistics = new Statistics(LocalDateTime.now(), INITIAL_DOWNLOADS);
        // String name, String description, String filename, byte[] data, Category category, Statistics statistics, Map<Integer, Image> images
//        Program newProgram = new Program(programForm.getName(), programForm.getDescription(), programForm.getFile().getOriginalFilename(),
//                programForm.getFile().getBytes(), category, statistics, new HashMap<>());
//        programManager.addProgram(newProgram);
        redirect.addFlashAttribute("successMessage", websiteMessages.getMessage("msg.program.added"));
        return REDIRECT_TO_SUBMIT_PAGE;
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
