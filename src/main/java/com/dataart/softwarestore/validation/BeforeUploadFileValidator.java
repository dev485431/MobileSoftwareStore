package com.dataart.softwarestore.validation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Component
public class BeforeUploadFileValidator {

    private static final Logger LOG = Logger.getLogger(AfterUploadFilesValidator.class);

    @Value("#{'${program.zip.expected.files}'.split(',')}")
    private List<String> zipExpectedFiles;

    public boolean containsExpectedFiles(CommonsMultipartFile file) {
        List<String> filenames = getFilenames(file);
        return containsExpectedNumberOfFiles(filenames) ? (containsExpectedFiles(filenames) ? true : false) : false;
    }

    private List<String> getFilenames(CommonsMultipartFile file) {
        List<String> filenames = new LinkedList<>();
        try (ZipInputStream zipStream = new ZipInputStream(file.getInputStream())) {
            ZipEntry zipEntry;
            int entryCounter = 0;
            while ((zipEntry = zipStream.getNextEntry()) != null && entryCounter <= zipExpectedFiles.size()) {
                filenames.add(zipEntry.getName());
                entryCounter++;
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
