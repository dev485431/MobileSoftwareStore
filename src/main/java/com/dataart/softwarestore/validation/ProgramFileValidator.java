package com.dataart.softwarestore.validation;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Component
public class ProgramFileValidator {

    private static final Logger LOG = Logger.getLogger(ProgramFileValidator.class);

    public boolean isValidFile(CommonsMultipartFile file) {
        Set<String> fileNames = new HashSet<>();
        try (ZipInputStream zis = new ZipInputStream(file.getInputStream())) {
            ZipEntry ze;
            while ((ze = zis.getNextEntry()) != null) {
                fileNames.add(ze.getName());
                LOG.debug("ZIP ENTRY: " + ze.toString());
            }
            LOG.debug("File names: " + fileNames);
        } catch (IOException e) {
            LOG.error("Error reading zip file: " + e.getMessage());
            return false;
        }
        return true;
    }

    // #1 file count
    // #2 contains
    // #3 hashmap <file name, how many>



}
