package com.dataart.softwarestore.web;

import com.dataart.softwarestore.model.dto.UploadStatusDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class UploadController {

    private static final Logger LOG = LoggerFactory.getLogger(UploadController.class);
    public static final String ROOT = "uploads";

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public UploadStatusDto handleFileUpload(@RequestParam("file") MultipartFile file) {

        UploadStatusDto uploadStatus = null;
        if (!file.isEmpty()) {
            try {
                Files.copy(file.getInputStream(), Paths.get(ROOT, file.getOriginalFilename()));
            } catch (IOException e) {
                uploadStatus = new UploadStatusDto(false, "Failed to upload " + file.getOriginalFilename());
                LOG.error(e.getStackTrace().toString());
            }
        } else {
            uploadStatus = new UploadStatusDto(false, "Failed to upload " + file.getOriginalFilename() + " because it was empty");
        }
        return uploadStatus;
    }

}
