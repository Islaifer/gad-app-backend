package com.fatec.gad.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Role {
    @Id @GeneratedValue
    private Long id;
    @Column
    private String name;

    public Role(String name){
        this.name = name;
    }
}
