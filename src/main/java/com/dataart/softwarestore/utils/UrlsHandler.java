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
        } catch (URISyntaxException | MalformedURLException e) {
            LOG.error("Failed to prepare url: " + e.getMessage());
        }
        return url;
    }

    public URL getUrl(Integer programId, UrlType urlType) {
        ProgramDetailsDto programDetails = programManager.getProgramDetailsById(programId);
        URL targetUrl = null;
        switch (urlType) {
            case PROGRAM_FILE:
                targetUrl = prepareURL(programDetails, zipInnerAppFile);
                break;
            case IMG128:
                targetUrl = prepareURL(programDetails, programDetails.getImg128());
                break;
            case IMG512:
                targetUrl = prepareURL(programDetails, programDetails.getImg512());
                break;
        }
        return targetUrl;
    }

    private URL prepareURL(ProgramDetailsDto programDetails, String filename) {
        URL url = null;
        try {
            URI uri = new URI(
                    "http",
                    programsMainUrlDomain,
                    programsMainUrlPath + programDetails.getName() + "/" + filename,
                    null);
            url = uri.toURL();
        } catch (URISyntaxException | MalformedURLException e) {
            LOG.error("Failed to prepare url: " + e.getMessage());
        }
        return url;
    }

}
