package com.dataart.softwarestore.model.dto;

public class ProgramDetailsDto {

    private Integer id;
    private String name;
    private String description;
    private String img128;
    private String img512;
    private String categoryName;
    private String timeUploaded;
    private Long downloads;

    public ProgramDetailsDto(Integer id, String name, String description, String img128, String img512, String
            categoryName, String timeUploaded, Long downloads) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.img128 = img128;
        this.img512 = img512;
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

    public String getImg128() {
        return img128;
    }

    public void setImg128(String img128) {
        this.img128 = img128;
    }

    public String getImg512() {
        return img512;
    }

    public void setImg512(String img512) {
        this.img512 = img512;
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
