package com.dataart.softwarestore.web;

import com.dataart.softwarestore.model.dto.UploadStatusDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/program")
public class ProgramController {

    private static final Logger LOG = LoggerFactory.getLogger(ProgramController.class);

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public UploadStatusDto handleFileUpload(@RequestParam("file") MultipartFile file) {

        return null;
    }

}
