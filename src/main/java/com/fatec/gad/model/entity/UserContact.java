package com.fatec.gad.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class UserContact {
    @Id @GeneratedValue
    private Long id;

    @Column
    private String street;

    @Column
    private String district;

    @Column
    private String cep;

    @Column
    private Integer houseNumber;

    @Column
    private String cellNumber;

    @Column
    private String alternativeCellNumber;

    @Column
    private String fullNameAlternativeContact;
}
