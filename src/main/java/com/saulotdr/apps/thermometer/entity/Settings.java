package com.saulotdr.apps.thermometer.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String city;
    @Column
    private Float boilingThreshold;
    @Column
    private Float freezingThreshold;
    @Column
    private boolean active;
}