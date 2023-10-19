package com.fatec.gad.model.request;

import com.fatec.gad.model.entity.Sick;
import com.fatec.gad.model.entity.UserPersonalData;
import com.fatec.gad.model.entity.Vehicle;
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

    private String crm;

    private Date birthdate;

    private String sex;

    private String nationality;

    private String bloodType;

    private List<SickRequest> sicks;

    private List<VehicleRequest> vehicles;

    public void clone(UserPersonalData data){
        this.id = data.getId();
        this.fullName = data.getFullName();
        this.cpf = data.getCpf();
        this.rg = data.getRg();
        this.crm = data.getCrm();
        this.birthdate = data.getBirthdate();
        this.sex = data.getSex();
        this.nationality = data.getNationality();
        this.bloodType = data.getBloodType();
        cloneSickList(data.getSicks());
        cloneVehicleList(data.getVehicles());
    }

    private void cloneSickList(List<Sick> list){
        SickRequest sick;
        this.sicks = new LinkedList<>();
        for(Sick var : list){
            sick = new SickRequest();
            sick.clone(var);
            this.sicks.add(sick);
        }
    }

    private void cloneVehicleList(List<Vehicle> list){
        VehicleRequest vehicle;
        this.vehicles = new LinkedList<>();
        for(Vehicle var : list){
            vehicle = new VehicleRequest();
            vehicle.clone(var);
            this.vehicles.add(vehicle);
        }
    }
}
