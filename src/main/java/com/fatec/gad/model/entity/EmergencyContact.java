package com.fatec.gad.model.entity;

import com.fatec.gad.model.request.EmergencyContactRequest;
import com.fatec.gad.model.request.UserRequest;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
public class EmergencyContact {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    private User user;

    @Column
    private String name;

    @Column
    private String obs;

    @Column
    private String cellNumber;

    public void clone(EmergencyContactRequest data){
        this.name = data.getName();
        this.obs = data.getObs();
        this.cellNumber = data.getCellNumber();
        this.user = new User();
        if(data.getUserRequest() != null) this.user.clone(data.getUserRequest());
    }
}
