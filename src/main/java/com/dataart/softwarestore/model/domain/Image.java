package com.dataart.softwarestore.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String filename;
    private Integer size;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    public Image() {
    }

    public Image(String filename, Integer size, Program program) {
        this.filename = filename;
        this.size = size;
        this.program = program;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
