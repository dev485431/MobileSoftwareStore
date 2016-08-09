package com.dataart.softwarestore.model.domain;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "statistics")
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "time_uploaded")
    private OffsetDateTime timeUploaded;
    private Long downloads;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "statistics_id", referencedColumnName = "id")
    private List<Rating> ratings;

    public Statistics() {
    }

    public Statistics(OffsetDateTime timeUploaded, Long downloads) {
        this.timeUploaded = timeUploaded;
        this.downloads = downloads;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OffsetDateTime getTimeUploaded() {
        return timeUploaded;
    }

    public void setTimeUploaded(OffsetDateTime timeUploaded) {
        this.timeUploaded = timeUploaded;
    }

    public Long getDownloads() {
        return downloads;
    }

    public void setDownloads(Long downloads) {
        this.downloads = downloads;
    }
}
