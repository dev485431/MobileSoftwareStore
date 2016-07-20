package com.dataart.softwarestore.validation;

import com.dataart.softwarestore.model.dto.ProgramForm;
import com.dataart.softwarestore.service.ProgramManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ProgramFormValidator extends LocalValidatorFactoryBean {

    private static final Logger LOG = Logger.getLogger(ProgramFormValidator.class);
    private static final String MIME_APPLICATION_ZIP = "application/zip";
    @Autowired
    private ProgramManager programManager;
    @Value("${uploaded.file.max.size.bytes}")
    private Long uploadedFileMaxSizeBytes;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(ProgramForm.class);
    }

    @Override
    public void validate(Object target, Errors errors, final Object... validationHints) {
        super.validate(target, errors, validationHints);
        if (!errors.hasErrors()) {
            ProgramForm programForm = (ProgramForm) target;
            MultipartFile programFile = ((ProgramForm) target).getFile();

            LOG.warn("Content type: " + programFile.getContentType());
            LOG.warn("File size: " + programFile.getSize());
            LOG.warn("File name: " + programFile.getOriginalFilename());

            if (!programFile.getContentType().equals(MIME_APPLICATION_ZIP)) {
                errors.rejectValue("file", "error.file.extension");
            }
            if (programFile == null || programFile.isEmpty()) {
                errors.rejectValue("file", "error.empty.file");
            } else if (programFile.getSize() > uploadedFileMaxSizeBytes) {
                errors.rejectValue("file", "error.file.size");
            }
            if (programManager.programNameExists(programForm.getName())) {
                errors.rejectValue("name", "error.program.name.exists");
            }
        }
    }
}
