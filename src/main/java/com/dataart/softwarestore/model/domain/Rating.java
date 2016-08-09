package com.dataart.softwarestore.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Float rating;

    @Column(name = "statistics_id")
    private Integer statisticsId;

    public Rating() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getStatisticsId() {
        return statisticsId;
    }

    public void setStatisticsId(Integer statisticsId) {
        this.statisticsId = statisticsId;
    }
}
