package com.dataart.softwarestore.model.domain;

import javax.persistence.*;
import java.util.Map;
import java.util.Optional;

@Entity
@Table(name = "programs")
public class Program {

    @Id
    @GeneratedValue
    @Column(name = "program_id")
    private Long id;

    // column with foreign keys to ProgramDetails
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="details_id")
    private ProgramDetails programDetails;

    @OneToMany(cascade = CascadeType.ALL)
    private Map<Integer, ProgramPicture> programPictures;


}
