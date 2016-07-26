package com.dataart.softwarestore.validation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Component
public class ProgramFileValidator {

    private static final Logger LOG = Logger.getLogger(ProgramFileValidator.class);

    @Value("#{'${program.zip.expected.files}'.split(',')}")
    private List<String> zipExpectedFiles;

    public boolean isValidFile(CommonsMultipartFile file) {
        List<String> filenames = getFilenames(file);
        return containsExpectedNumberOfFiles(filenames) ? (containsExpectedFiles(filenames) ? true : false) : false;
    }

    public List<String> getFilenames(CommonsMultipartFile file) {
        List<String> filenames = new LinkedList<>();
        try (ZipInputStream zis = new ZipInputStream(file.getInputStream())) {
            ZipEntry ze;
            while ((ze = zis.getNextEntry()) != null) {
                filenames.add(ze.getName());
            }
        } catch (IOException e) {
            LOG.error("Error reading zip file: " + e.getMessage());
        }
        return filenames;
    }

    private boolean containsExpectedNumberOfFiles(List<String> filenames) {
        return filenames.size() == zipExpectedFiles.size();
    }

    private boolean containsExpectedFiles(List<String> filenames) {
        for (String filename : zipExpectedFiles) {
            if (!filenames.contains(filename)) return false;
        }
        return true;
    }

}
