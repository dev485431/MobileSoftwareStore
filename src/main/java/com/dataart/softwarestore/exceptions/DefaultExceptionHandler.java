package com.dataart.softwarestore.exceptions;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger LOG = Logger.getLogger(DefaultExceptionHandler.class);
    private static final int SIZE_DIVIDER = 1024;
    private static final String DEFAULT_ERROR_VIEW = "error";
    private static final String VIEW_404 = "error404";
    private static final String SUBMIT_PROGRAM_VIEW = "submit";
    private static final String REDIRECT_SUBMIT_FORM = "redirect:/submit";
    @Autowired
    private MessageSourceAccessor websiteMessages;

    @ExceptionHandler(ProgramFileUploadException.class)
    public String handleProgramFileUploadException(RedirectAttributes redirect) {
        redirect.addFlashAttribute("errorMessage", websiteMessages.getMessage("exception.ftp.file.upload"));
        return REDIRECT_SUBMIT_FORM;
    }

    @ExceptionHandler(MultipartException.class)
    public RedirectView handleMultipartException(Exception ex, HttpServletRequest request) {
        RedirectView model = new RedirectView(SUBMIT_PROGRAM_VIEW);
        FlashMap flash = RequestContextUtils.getOutputFlashMap(request);
        MultipartException mEx = (MultipartException) ex;

        if (ex.getCause() instanceof FileUploadBase.FileSizeLimitExceededException) {
            FileUploadBase.FileSizeLimitExceededException flEx = (FileUploadBase.FileSizeLimitExceededException) mEx
                    .getCause();
            float permittedSize = flEx.getPermittedSize() / (float) SIZE_DIVIDER;
            String message = websiteMessages.getMessage(
                    "error.file.maxsize",
                    new Object[]{flEx.getFileName(), permittedSize});
            flash.put("fileError", message);
        } else {
            flash.put("error", websiteMessages.getMessage("msg.contact.admin") +
                    ex.getMessage());
        }
        return model;
    }

    @ExceptionHandler(value = IOException.class)
    public RedirectView handleIOException(Exception ex, HttpServletRequest request) {
        RedirectView model = new RedirectView(SUBMIT_PROGRAM_VIEW);
        FlashMap flash = RequestContextUtils.getOutputFlashMap(request);
        flash.put("fileError", websiteMessages.getMessage("error.file.io") + ex
                .getMessage());
        return model;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String requestHandlingNoHandlerFound() {
        return VIEW_404;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultExceptionHandler(HttpServletRequest req, Exception ex) throws Exception {
        LOG.error(String.format("Exception : %s. Cause: %s", ex.getMessage(), ex.getCause()));
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

}
