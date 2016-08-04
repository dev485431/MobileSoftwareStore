package com.dataart.softwarestore.utils;

import com.dataart.softwarestore.model.dto.ProgramDetailsDto;
import com.dataart.softwarestore.service.ProgramManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Component
public class UrlsHandler {

    @Autowired
    private ProgramManager programManager;
    @Value("${programs.main.url.domain}")
    private String programsMainUrlDomain;
    @Value("${programs.main.url.path}")
    private String programsMainUrlPath;

    // need download, 128, 512 urls from this method
    public URL getUrl(Integer programId, UrlType urlType) {
        ProgramDetailsDto programDetails = programManager.getProgramDetailsById(programId);
        switch (urlType) {
            case PROGRAM_FILE:

                break;

            case IMG128:

                break;

            case IMG512:

                break;

            default:

                break;
        }

        return null;
    }

    private URL createURL(ProgramDetailsDto programDetails, String filename) throws MalformedURLException,
            URISyntaxException {
        URI uri = new URI(
                "http",
                programsMainUrlDomain,
                programsMainUrlPath + programDetails.getName() + "/" + filename,
                null);
        return uri.toURL();
    }

}
