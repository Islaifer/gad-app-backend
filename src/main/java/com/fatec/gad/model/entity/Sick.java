package com.fatec.gad.model.entity;

import com.fatec.gad.model.request.SickRequest;
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

    public void clone(SickRequest data){
        this.name = data.getName();
        this.type = data.getType();
        this.obs = data.getObs();
    }
}
