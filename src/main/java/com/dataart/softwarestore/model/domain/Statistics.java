package com.dataart.softwarestore.model.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "statistics")
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "time_uploaded")
    private Date timeUploaded;
    private Long downloads;

    public Statistics() {
    }

    public Statistics(Date timeUploaded, Long downloads) {
        this.timeUploaded = timeUploaded;
        this.downloads = downloads;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimeUploaded() {
        return timeUploaded;
    }

    public void setTimeUploaded(Date timeUploaded) {
        this.timeUploaded = timeUploaded;
    }

    public Long getDownloads() {
        return downloads;
    }

    public void setDownloads(Long downloads) {
        this.downloads = downloads;
    }
}
