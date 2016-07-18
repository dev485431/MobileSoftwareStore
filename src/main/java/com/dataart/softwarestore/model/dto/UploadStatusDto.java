package com.dataart.softwarestore.model.dto;

public class UploadStatusDto {

    boolean successful;
    String message;

    public UploadStatusDto() {
    }

    public UploadStatusDto(boolean successful, String message) {
        this.successful = successful;
        this.message = message;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
