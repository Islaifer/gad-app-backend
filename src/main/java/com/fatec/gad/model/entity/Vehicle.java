package com.fatec.gad.model.entity;

import com.fatec.gad.model.request.VehicleRequest;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Vehicle {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String model;

    @Column
    private int year;

    @Column
    private String plate;

    @ManyToOne(cascade = CascadeType.ALL)
    private UserPersonalData userPersonalData;

    public void clone(VehicleRequest data){
        this.name = data.getName();
        this.model = data.getModel();
        this.year = data.getYear();
        this.plate = data.getPlate();
    }
}
