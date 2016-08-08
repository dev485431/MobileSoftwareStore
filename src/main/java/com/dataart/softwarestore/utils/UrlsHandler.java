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
    private static final String PROTOCOL = "http";
    @Autowired
    private ProgramManager programManager;
    @Value("${programs.main.url.domain}")
    private String programsMainUrlDomain;
    @Value("${programs.main.url.path}")
    private String programsMainUrlPath;
    @Value("${program.default.images.path}")
    private String programsDefaultImagesPath;
    @Value("${program.zip.inner.app.filename}")
    private String zipInnerAppFile;

    public URL getUrl(UrlType urlType) {
        URI uri;
        URL url = null;

        try {
            switch (urlType) {
                case MAIN_PROGRAMS_URL:
                    uri = new URI(
                            PROTOCOL,
                            programsMainUrlDomain,
                            programsMainUrlPath,
                            null);
                    url = uri.toURL();
                    break;

                case DEFAULT_IMAGES_URL:
                    uri = new URI(
                            PROTOCOL,
                            programsMainUrlDomain,
                            programsDefaultImagesPath,
                            null);
                    url = uri.toURL();
                    break;
            }
        } catch (URISyntaxException | MalformedURLException e) {
            LOG.error("Failed to prepare url: " + e.getMessage());
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
