package com.fatec.gad.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity @Data
public class Sick {
    @Id @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private String obs;

    @ManyToOne(cascade = CascadeType.ALL)
    private UserPersonalData userPersonalData;
}
