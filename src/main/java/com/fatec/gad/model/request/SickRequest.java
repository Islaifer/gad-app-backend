package com.fatec.gad.model.request;

import com.fatec.gad.model.entity.Sick;
import lombok.Data;
import org.springframework.stereotype.Component;


@Component @Data
public class SickRequest {
    private Long id;

    private String name;

    private String type;

    private String obs;

    public void clone(Sick data){
        this.id = data.getId();
        this.name = data.getName();
        this.type = data.getType();
        this.obs = data.getObs();
    }
}
