package com.dataart.softwarestore.model.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "statistics")
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "time_uploaded")
    private LocalDateTime timeUploaded;
    private Long downloads;

    public Statistics() {
    }

    public Statistics(LocalDateTime timeUploaded, Long downloads) {
        this.timeUploaded = timeUploaded;
        this.downloads = downloads;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getTimeUploaded() {
        return timeUploaded;
    }

    public void setTimeUploaded(LocalDateTime timeUploaded) {
        this.timeUploaded = timeUploaded;
    }

    public Long getDownloads() {
        return downloads;
    }

    public void setDownloads(Long downloads) {
        this.downloads = downloads;
    }
}
