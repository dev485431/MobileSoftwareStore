package com.dataart.softwarestore.validation;

import com.dataart.softwarestore.model.dto.ProgramForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProgramFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(ProgramForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
