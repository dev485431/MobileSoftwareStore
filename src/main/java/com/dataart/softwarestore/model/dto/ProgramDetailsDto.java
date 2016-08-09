package com.dataart.softwarestore.model.dto;

import java.net.URL;

public class ProgramDetailsDto {

    private Integer id;
    private String name;
    private String description;
    private URL img128Url;
    private URL img512Url;
    private URL downloadUrl;
    private String categoryName;
    private String timeUploaded;
    private Long downloads;

    public ProgramDetailsDto(Integer id, String name, String description, URL img128Url, URL img512Url, URL
            downloadUrl, String categoryName, String timeUploaded, Long downloads) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.img128Url = img128Url;
        this.img512Url = img512Url;
        this.downloadUrl = downloadUrl;
        this.categoryName = categoryName;
        this.timeUploaded = timeUploaded;
        this.downloads = downloads;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public URL getImg128Url() {
        return img128Url;
    }

    public void setImg128Url(URL img128Url) {
        this.img128Url = img128Url;
    }

    public URL getImg512Url() {
        return img512Url;
    }

    public void setImg512Url(URL img512Url) {
        this.img512Url = img512Url;
    }

    public URL getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(URL downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTimeUploaded() {
        return timeUploaded;
    }

    public void setTimeUploaded(String timeUploaded) {
        this.timeUploaded = timeUploaded;
    }

    public Long getDownloads() {
        return downloads;
    }

    public void setDownloads(Long downloads) {
        this.downloads = downloads;
    }
}
