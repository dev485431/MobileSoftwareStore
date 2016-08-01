package com.dataart.softwarestore.validation;

import com.dataart.softwarestore.model.dto.ProgramTextDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProgramTextDetailsValidator {

    public boolean isValid(ProgramTextDetails programTextDetails) throws IOException {
        return programTextDetails.getProgramName().isPresent() ? true : false;
    }
}
