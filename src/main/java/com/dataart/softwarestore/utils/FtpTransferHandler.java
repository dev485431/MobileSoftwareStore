package com.dataart.softwarestore.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class FtpTransferHandler {

    private static final Logger LOG = Logger.getLogger(FtpTransferHandler.class);
    @Value("${ftp.host}")
    private String ftpHost;
    @Value("${ftp.user}")
    private String ftpUser;
    @Value("${ftp.password}")
    private String ftpPass;
    @Value("${ftp.upload.dir.path}")
    private String uploadDirPath;

    public void uploadFiles(List<File> files) throws IOException {
        FTPClient ftp = new FTPClient();
        ftp.connect(ftpHost);
        ftp.enterLocalPassiveMode();

        if (!ftp.login(ftpUser, ftpPass)) {
            LOG.error("Failed to log in: " + ftp.getReplyString());
        }

        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        for (File file : files) {
            LOG.debug("Transferring file by ftp: " + file);
            InputStream in = new FileInputStream(file);
            if (!ftp.storeFile(uploadDirPath + file.getName(), in)) {
                LOG.error("Ftp file transfer failed: " + ftp.getReplyString());
            }
            in.close();
        }
        ftp.disconnect();
    }
}
