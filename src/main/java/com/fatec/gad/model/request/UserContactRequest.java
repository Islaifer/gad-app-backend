package com.fatec.gad.model.request;

import com.fatec.gad.model.entity.UserContact;
import lombok.Data;
import org.springframework.stereotype.Component;


@Component @Data
public class UserContactRequest {
    private Long id;

    private String street;

    private String district;

    private String cep;

    private Integer houseNumber;

    private String cellNumber;

    private String alternativeCellNumber;

    private String fullNameAlternativeContact;

    public void clone(UserContact data){
        this.id = data.getId();
        this.street = data.getStreet();
        this.district = data.getDistrict();
        this.cep = data.getCep();
        this.houseNumber = data.getHouseNumber();
        this.cellNumber = data.getCellNumber();
        this.alternativeCellNumber = data.getAlternativeCellNumber();
        this.fullNameAlternativeContact = data.getFullNameAlternativeContact();
    }
}
