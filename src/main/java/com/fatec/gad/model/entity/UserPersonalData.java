package com.fatec.gad.model.entity;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity @Data
public class UserPersonalData {
    @Id @GeneratedValue
    private Long id;

    @Column
    private String fullName;

    @Column
    private String cpf;

    @Column
    private String rg;

    @Column
    private String coren;

    @Column
    private String crm;

    @Column
    private Date birthdate;

    @Column
    private String sex;

    @Column
    private String nationality;

    @Column
    private String bloodType;

    @OneToMany(mappedBy = "userPersonalData", cascade=CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column
    private List<Sick> sicks;
}
