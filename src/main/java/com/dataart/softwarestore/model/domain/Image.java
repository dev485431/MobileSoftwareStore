package com.dataart.softwarestore.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private Integer size;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

}
