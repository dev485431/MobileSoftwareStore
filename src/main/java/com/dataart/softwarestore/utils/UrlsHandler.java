package com.dataart.softwarestore.utils;

import com.dataart.softwarestore.model.dto.ProgramDetailsDto;
import com.dataart.softwarestore.service.ProgramManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Component
public class UrlsHandler {

    private static final Logger LOG = Logger.getLogger(UrlsHandler.class);
    @Autowired
    private ProgramManager programManager;
    @Value("${programs.main.url.domain}")
    private String programsMainUrlDomain;
    @Value("${programs.main.url.path}")
    private String programsMainUrlPath;
    @Value("${program.zip.inner.app.filename}")
    private String zipInnerAppFile;

    public URL getMainProgramsUrl() {
        URL url = null;
        try {
            URI uri = new URI(
                    "http",
                    programsMainUrlDomain,
                    programsMainUrlPath,
                    null);
            url = uri.toURL();
            LOG.debug("Main programs url is: " + url);
        } catch (URISyntaxException | MalformedURLException e) {
            LOG.error("Failed to prepare main programs url: " + e.getMessage());
        }
        return url;
    }

    public URL getProgramDownloadUrl(Integer programId) {
        ProgramDetailsDto programDetails = programManager.getProgramDetailsById(programId);
        URL url = null;
        try {
            URI uri = new URI(
                    "http",
                    programsMainUrlDomain,
                    programsMainUrlPath + programDetails.getName() + "/" + zipInnerAppFile,
                    null);
            url = uri.toURL();
            LOG.debug("Prepared program download url: " + url);
        } catch (URISyntaxException | MalformedURLException e) {
            LOG.error("Failed to prepare download url: " + e.getMessage());
        }
        return url;
    }

}
