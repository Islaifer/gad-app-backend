package com.fatec.gad.model.entity;

import com.fatec.gad.model.request.EmergencyContactRequest;
import com.fatec.gad.model.request.SickRequest;
import com.fatec.gad.model.request.UserPersonalDataRequest;
import com.fatec.gad.model.request.VehicleRequest;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
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
    private String crm;

    @Column
    private Date birthdate;

    @Column
    private String sex;

    @Column
    private String nationality;

    @Column
    private String bloodType;

    @OneToMany(mappedBy = "userPersonalData",cascade = CascadeType.ALL )
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column
    @ToString.Exclude
    private List<EmergencyContact> userEmergencyContacts;

    @OneToMany(mappedBy = "userPersonalData", cascade=CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column
    @ToString.Exclude
    private List<Sick> sicks;

    @OneToMany(mappedBy = "userPersonalData", cascade=CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column
    @ToString.Exclude
    private List<Vehicle> vehicles;

    public void clone(UserPersonalDataRequest data){
        this.fullName = data.getFullName();
        this.cpf = data.getCpf();
        this.rg = data.getRg();
        this.crm = data.getCrm();
        this.birthdate = data.getBirthdate();
        this.sex = data.getSex();
        this.nationality = data.getNationality();
        this.bloodType = data.getBloodType();
        if(data.getSicks() != null) cloneSickList(data.getSicks());
        if(data.getVehicles() != null) cloneVehicleList(data.getVehicles());
        if(data.getEmergencyContactRequests() != null) cloneContactList(data.getEmergencyContactRequests());
    }

    private void cloneSickList(List<SickRequest> list){
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

    private void cloneVehicleList(List<VehicleRequest> list){
        Vehicle vehicle;
        this.vehicles = new LinkedList<>();
        for(VehicleRequest var : list){
            vehicle = new Vehicle();
            vehicle.clone(var);
            this.vehicles.add(vehicle);
        }
    }

    private void cloneContactList(List<EmergencyContactRequest> list){
        EmergencyContact emergencyContact;
        this.userEmergencyContacts = new LinkedList<>();
        for(EmergencyContactRequest var : list){
            emergencyContact = new EmergencyContact();
            emergencyContact.clone(var);
            this.userEmergencyContacts.add(emergencyContact);
        }
    }
}
