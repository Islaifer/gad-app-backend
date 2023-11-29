package com.fatec.gad.model.request;

import com.fatec.gad.model.entity.Vehicle;
import lombok.Data;
import org.springframework.stereotype.Component;


@Component
@Data
public class VehicleRequest {
    private Long id;

    private String name;

    private String model;

    private int year;

    private String plate;

    private UserPersonalDataRequest userPersonalDataRequest;

    public void clone(Vehicle data){
        this.name = data.getName();
        this.model = data.getModel();
        this.year = data.getYear();
        this.plate = data.getPlate();
    }
}
