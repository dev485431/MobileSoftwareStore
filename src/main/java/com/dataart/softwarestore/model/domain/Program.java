package com.dataart.softwarestore.model.domain;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "programs")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String filename;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable=false, columnDefinition="mediumblob")
    private byte[] data;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "statistics_id")
    private Statistics statistics;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "program")
    @MapKey(name = "size")
    private Map<Integer, Image> images;

    public Program() {
    }

    public Program(String name, String description, String filename, byte[] data, Category category, Statistics statistics, Map<Integer, Image> images) {
        this.name = name;
        this.description = description;
        this.filename = filename;
        this.data = data;
        this.category = category;
        this.statistics = statistics;
        this.images = images;
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public Map<Integer, Image> getImages() {
        return images;
    }

    public void setImages(Map<Integer, Image> images) {
        this.images = images;
    }
}
