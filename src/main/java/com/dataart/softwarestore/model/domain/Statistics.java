package com.dataart.softwarestore.model.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "statistics")
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_uploaded")
    private Date timeUploaded;
    private Long downloads;

    @OneToOne(mappedBy = "statistics")
    private Program program;


}
