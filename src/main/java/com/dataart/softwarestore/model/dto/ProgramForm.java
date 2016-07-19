package com.dataart.softwarestore.model.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.dataart.softwarestore.validation.ValidationConfig.*;

public class ProgramForm {

    @NotBlank(message = "{msg.empty}")
    @Size(min = LENGTH_PROGRAM_NAME_MIN, max = LENGTH_PROGRAM_NAME_MAX, message = "{msg.invalid.length}")
    @Pattern(regexp = REGEXP_ALPHANUMERIC, message = "{msg.invalid.program.name}")
    private String name;

    @NotBlank(message = "{msg.empty}")
    private MultipartFile file;

    @NotBlank(message = "{msg.empty}")
    @Size(min = LENGTH_DESCRIPTION_MIN, max = LENGTH_DESCRIPTION_MAX, message = "{msg.invalid.length}")
    @Pattern(regexp = REGEXP_ALPHANUMERIC, message = "{msg.invalid.program.description}")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
