package com.dataart.softwarestore.utils;

import com.dataart.softwarestore.model.dto.ProgramTextDetails;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

@Component
public class ProgramInfoHandler {

    private static final String KEY_NAME = "name";
    private static final String KEY_PICTURE_128 = "picture_128";
    private static final String KEY_PICTURE_512 = "picture_512";


    public ProgramTextDetails getProgramTextDetails(File programTextFile) throws IOException {
        Properties prop = loadProgramProperties(programTextFile);
        return new ProgramTextDetails(Optional.ofNullable(prop.getProperty(KEY_NAME)), Optional.ofNullable(prop.getProperty(KEY_PICTURE_128)), Optional.ofNullable(prop.getProperty(KEY_PICTURE_512)));
    }

    private Properties loadProgramProperties(File programTextFile) throws IOException {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(programTextFile)) {
            prop.load(input);
        }
        return prop;
    }


}
