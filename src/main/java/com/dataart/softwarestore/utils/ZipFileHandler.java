package com.dataart.softwarestore.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Component
public class ZipFileHandler {

    private static final Logger LOG = Logger.getLogger(ZipFileHandler.class);

    public File transferFileToDir(CommonsMultipartFile sourceFile, String targetDir) throws IOException {
        if (!new File(targetDir).exists()) new File(targetDir).mkdir();
        File targetFile = new File(targetDir + "/" + sourceFile.getOriginalFilename());
        LOG.debug("Transferring program file to: " + targetFile.getAbsolutePath());
        sourceFile.transferTo(targetFile);
        return targetFile;
    }

    public void extractZipFile(File file) throws IOException {
        ZipFile zipFile = new ZipFile(file);

        try {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                File entryDestination = new File(FilenameUtils.removeExtension(file.getAbsolutePath()), entry.getName());
                if (entry.isDirectory()) {
                    entryDestination.mkdirs();
                } else {
                    entryDestination.getParentFile().mkdirs();
                    InputStream in = zipFile.getInputStream(entry);
                    OutputStream out = new FileOutputStream(entryDestination);
                    IOUtils.copy(in, out);
                    IOUtils.closeQuietly(in);
                    out.close();
                }
            }
        } finally {
            zipFile.close();
        }

    }
}
