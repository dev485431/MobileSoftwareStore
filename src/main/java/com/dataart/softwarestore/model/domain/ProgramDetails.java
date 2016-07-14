package com.dataart.softwarestore.model.domain;

import javax.persistence.*;
import java.util.Map;
import java.util.Optional;

@Entity
@Table(name = "programs_details")
public class ProgramDetails {

    @Id
    @GeneratedValue
    @Column(name = "details_id")
    private Long id;
    @Column(name = "program_name")
    private String programName;
    @Column(name = "package_name")
    private String packageName;

    // one to one but managed on the other side
    @OneToOne(mappedBy = "programDetails")
    private Program program;


}
