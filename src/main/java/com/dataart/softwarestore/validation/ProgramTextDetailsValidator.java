package com.dataart.softwarestore.validation;

import com.dataart.softwarestore.model.dto.ProgramTextDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProgramTextDetailsValidator {

    public boolean isValid(ProgramTextDetails programTextDetails) throws IOException {
        if (programTextDetails.getProgramName().isPresent() && !programTextDetails.getProgramName().get().isEmpty()
                && programTextDetails.getPackageName().isPresent() && !programTextDetails.getPackageName().get().isEmpty()) {
            return true;
        }
        return false;
    }
}
