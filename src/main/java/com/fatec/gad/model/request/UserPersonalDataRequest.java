package com.fatec.gad.model.request;

import com.fatec.gad.model.entity.Sick;
import com.fatec.gad.model.entity.UserPersonalData;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Component @Data
public class UserPersonalDataRequest {
    private Long id;

    private String fullName;

    private String cpf;

    private String rg;

    private String coren;

    private String crm;

    private Date birthdate;

    private String sex;

    private String nationality;

    private String bloodType;

    private List<SickRequest> sicks;

    public void clone(UserPersonalData data){
        this.id = data.getId();
        this.fullName = data.getFullName();
        this.cpf = data.getCpf();
        this.rg = data.getRg();
        this.coren = data.getCoren();
        this.crm = data.getCrm();
        this.birthdate = data.getBirthdate();
        this.sex = data.getSex();
        this.nationality = data.getNationality();
        this.bloodType = data.getBloodType();
        cloneList(data.getSicks());
    }

    private void cloneList(List<Sick> list){
        SickRequest sick;
        this.sicks = new LinkedList<>();
        for(Sick var : list){
            sick = new SickRequest();
            sick.clone(var);
            this.sicks.add(sick);
        }
    }
}
