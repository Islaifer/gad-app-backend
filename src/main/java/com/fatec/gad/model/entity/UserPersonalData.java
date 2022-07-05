package com.fatec.gad.model.entity;

import com.fatec.gad.model.request.SickRequest;
import com.fatec.gad.model.request.UserPersonalDataRequest;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity @Data
public class UserPersonalData {
    @Id @GeneratedValue
    private Long id;

    @Column
    private String fullName;

    @Column
    private String cpf;

    @Column
    private String rg;

    @Column
    private String coren;

    @Column
    private String crm;

    @Column
    private Date birthdate;

    @Column
    private String sex;

    @Column
    private String nationality;

    @Column
    private String bloodType;

    @OneToMany(mappedBy = "userPersonalData", cascade=CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column
    private List<Sick> sicks;

    public void clone(UserPersonalDataRequest data){
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

    private void cloneList(List<SickRequest> list){
        Sick sick;
        this.sicks = new LinkedList<>();
        if(list != null){
            for(SickRequest var : list){
                sick = new Sick();
                sick.clone(var);
                this.sicks.add(sick);
            }
        }
    }
}
