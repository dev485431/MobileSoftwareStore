package com.dataart.softwarestore.validation;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Component
public class ProgramFileValidator {

    private static final Logger LOG = Logger.getLogger(ProgramFileValidator.class);

    public boolean isValidFile(CommonsMultipartFile file) throws IOException {
        ZipInputStream zis = new ZipInputStream(file.getInputStream());
        ZipEntry ze;
        while ((ze = zis.getNextEntry()) != null) {
            LOG.debug("ZIP ENTRY: " + ze.toString());
        }
        return false;
    }

}
