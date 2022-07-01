package com.fatec.gad.model.request;

import com.fatec.gad.model.entity.Role;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class RoleRequest {
    private Long id;
    private String name;

    public void clone(Role data){
        this.id = data.getId();
        this.name = data.getName();
    }
}
