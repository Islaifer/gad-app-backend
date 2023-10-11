package com.fatec.gad.model.request;

import com.fatec.gad.model.entity.EmergencyContact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmergencyContactRequest {
    private Long id;

    private UserRequest userRequest;

    private String name;

    private String obs;

    private String cellNumber;

    public EmergencyContactRequest(EmergencyContact data){
        clone(data);
    }

    private void clone(EmergencyContact data){
        this.name = data.getName();
        this.obs = data.getObs();
        this.cellNumber = data.getCellNumber();
        if(data.getUser() != null) this.userRequest = new UserRequest(data.getUser());
    }
}
