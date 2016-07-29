package com.dataart.softwarestore.validation;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Component
public class AfterUploadFilesValidator {

    private static final Logger LOG = Logger.getLogger(AfterUploadFilesValidator.class);

    public boolean areThereEmptyFiles(List<File> files) throws IOException {
        for (File file : files) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file.getAbsolutePath());
                if (fis.available() == 0) {
                    return true;
                }
            } finally {
                fis.close();
            }
        }
        return false;
    }


}
