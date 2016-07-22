package com.dataart.softwarestore.validation;

import com.dataart.softwarestore.model.dto.ProgramForm;
import com.dataart.softwarestore.service.ProgramManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ProgramFormValidator implements Validator {

    private static final Logger LOG = Logger.getLogger(ProgramFormValidator.class);
    private static final String ZIP_EXTENSION = ".zip";
    @Autowired
    private ProgramManager programManager;
    @Value("${uploaded.file.max.size.bytes}")
    private Long uploadedFileMaxSizeBytes;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(ProgramForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            ProgramForm programForm = (ProgramForm) target;
            MultipartFile programFile = ((ProgramForm) target).getFile();

            if (programFile.getSize() == 0) {
                LOG.debug("The file is empty");
                errors.rejectValue("file", "error.empty.file");
            }
            if (!programFile.getOriginalFilename().toLowerCase().endsWith(ZIP_EXTENSION)) {
                errors.rejectValue("file", "error.file.extension");
            }
            if (programManager.programNameExists(programForm.getName())) {
                LOG.debug("Program name already exists in the database");
                errors.rejectValue("name", "error.program.name.exists");
            }
        }
    }
}
