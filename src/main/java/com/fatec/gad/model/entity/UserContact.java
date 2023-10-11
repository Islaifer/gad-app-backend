package com.fatec.gad.model.entity;

import com.fatec.gad.model.request.UserContactRequest;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@RequiredArgsConstructor
public class UserContact {
    @Id @GeneratedValue
    private Long id;

    @Column
    private String street;

    @Column
    private String district;

    @Column
    private String cep;

    @Column
    private Integer houseNumber;

    @Column
    private String cellNumber;

    @Column
    private String alternativeCellNumber;

    @Column
    private String fullNameAlternativeContact;

    @Column
    private String uf;

    public void clone(UserContactRequest data){
        this.street = data.getStreet();
        this.district = data.getDistrict();
        this.cep = data.getCep();
        this.houseNumber = data.getHouseNumber();
        this.cellNumber = data.getCellNumber();
        this.alternativeCellNumber = data.getAlternativeCellNumber();
        this.fullNameAlternativeContact = data.getFullNameAlternativeContact();
        this.uf = data.getUf();
    }
}
