package com.dataart.softwarestore.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "pictures")
public class ProgramPicture {

    @Id
    @GeneratedValue
    @Column(name = "picture_id")
    private Long id;
    private String name;
    private Integer width;
    private Integer height;
    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    public ProgramPicture() {
    }

    public ProgramPicture(String name, Integer width, Integer height, Program program) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.program = program;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
