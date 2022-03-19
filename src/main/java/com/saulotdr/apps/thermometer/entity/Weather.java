package com.saulotdr.apps.thermometer.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "temperature")
    private Float temperature;
    @Column(name = "created_at")
    private Instant createdAt;
    @OneToOne
    @JoinColumn
    private Settings settings;
}