package com.dataart.softwarestore.exceptions;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.transaction.CannotCreateTransactionException;
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
    private static final String DEFAULT_ERROR_VIEW = "error";
    private static final String REDIRECT_DB_EXCEPTION = "error";
    private static final String VIEW_404 = "error404";
    private static final String MAX_SIZE_EXCEEDED_VIEW = "details";
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({MultipartException.class, FileUploadException.class})
    public RedirectView handleMultipartException(Exception ex, HttpServletRequest request) {
        LOG.warn("HANDLING EXCEPTION!");
        RedirectView model = new RedirectView(MAX_SIZE_EXCEEDED_VIEW);
        FlashMap flash = RequestContextUtils.getOutputFlashMap(request);
        if (ex instanceof MultipartException) {
            MultipartException mEx = (MultipartException) ex;

            if (ex.getCause() instanceof FileUploadBase.FileSizeLimitExceededException) {
                FileUploadBase.FileSizeLimitExceededException flEx = (FileUploadBase.FileSizeLimitExceededException) mEx.getCause();
                float permittedSize = flEx.getPermittedSize() / 1024;
                String message = messageSource.getMessage(
                        "error.file.maxsize",
                        new Object[]{flEx.getFileName(), permittedSize},
                        LocaleContextHolder.getLocale());
                flash.put("errors", message);
            } else {
                flash.put("errors", "Please contact your administrator: " + ex.getMessage());
            }
        } else {
            flash.put("errors", "Please contact your administrator: " + ex.getMessage());
        }
        return model;
    }

    @ExceptionHandler(value = IOException.class)
    public RedirectView handleIOException(Exception ex, HttpServletRequest request) {
        RedirectView model = new RedirectView(MAX_SIZE_EXCEEDED_VIEW);
        FlashMap flash = RequestContextUtils.getOutputFlashMap(request);
        flash.put("errors", "Please contact your administrator: " + ex.getMessage());
        return model;
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public String requestHandlingNoHandlerFound(HttpServletRequest req, NoHandlerFoundException ex) {
        return VIEW_404;
    }

    @ExceptionHandler({CommunicationsException.class, CannotCreateTransactionException.class, JDBCConnectionException.class})
    public String databaseConnectionExceptionHandler(Exception ex) throws Exception {
        LOG.error(String.format("Exception : %s. Cause: %s", ex.getMessage(), ex.getCause()));
        return REDIRECT_DB_EXCEPTION;
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
