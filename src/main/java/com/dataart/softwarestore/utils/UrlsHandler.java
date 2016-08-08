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
    private static final String BACKSLASH = "/";
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
    @Value("${program.default.img128}")
    private String defaultImg128;
    @Value("${program.default.img512}")
    private String defaultImg512;

    public URL getUrl(Integer programId, UrlType urlType) {
        ProgramDetailsDto programDetails = programManager.getProgramDetailsById(programId);
        URI uri;
        URL url = null;

        try {
            switch (urlType) {
                case PROGRAM_FILE_DOWNLOAD_URL:
                    uri = new URI(
                            PROTOCOL,
                            programsMainUrlDomain,
                            programsMainUrlPath + programDetails.getName() + BACKSLASH + zipInnerAppFile,
                            null);
                    url = uri.toURL();
                    break;

                case IMAGE_128:
                    // more logic; check if it's null
                    // use Optional in ProgramDetailsDto for images;
                    String path = programDetails.getImg128() == null ? programsDefaultImagesPath + BACKSLASH +
                            defaultImg128 : programsDefaultImagesPath + BACKSLASH + programDetails.getImg128();

                    uri = new URI(
                            PROTOCOL,
                            programsMainUrlDomain,
                            path,
                            null);
                    url = uri.toURL();
                    break;
            }
        } catch (URISyntaxException | MalformedURLException e) {
            LOG.error("Failed to prepare url: " + e.getMessage());
        }
        return url;
    }

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
