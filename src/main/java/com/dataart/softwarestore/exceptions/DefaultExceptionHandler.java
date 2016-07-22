package com.dataart.softwarestore.exceptions;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
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
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MultipartException.class)
    public RedirectView handleMultipartException(Exception ex, HttpServletRequest request) {
        RedirectView model = new RedirectView(SUBMIT_PROGRAM_VIEW);
        FlashMap flash = RequestContextUtils.getOutputFlashMap(request);
        if (ex instanceof MultipartException) {
            MultipartException mEx = (MultipartException) ex;

            if (ex.getCause() instanceof FileUploadBase.FileSizeLimitExceededException) {
                FileUploadBase.FileSizeLimitExceededException flEx = (FileUploadBase.FileSizeLimitExceededException) mEx.getCause();
                float permittedSize = flEx.getPermittedSize() / SIZE_DIVIDER;
                String message = messageSource.getMessage(
                        "error.file.maxsize",
                        new Object[]{flEx.getFileName(), permittedSize},
                        LocaleContextHolder.getLocale());
                flash.put("fileError", message);
            } else {
                flash.put("error", messageSource.getMessage("hint.contact.admin", null, LocaleContextHolder.getLocale()) + ex.getMessage());
            }
        } else {
            flash.put("error", messageSource.getMessage("hint.contact.admin", null, LocaleContextHolder.getLocale()) + ex.getMessage());
        }
        return model;
    }

    @ExceptionHandler(value = IOException.class)
    public RedirectView handleIOException(Exception ex, HttpServletRequest request) {
        RedirectView model = new RedirectView(SUBMIT_PROGRAM_VIEW);
        FlashMap flash = RequestContextUtils.getOutputFlashMap(request);
        flash.put("fileError", messageSource.getMessage("error.file.io", null, LocaleContextHolder.getLocale()));
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
