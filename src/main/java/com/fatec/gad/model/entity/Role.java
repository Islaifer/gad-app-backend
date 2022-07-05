package com.fatec.gad.model.entity;

import com.fatec.gad.model.request.RoleRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Role {
    @Id @GeneratedValue
    private Long id;
    @Column
    private String name;

    public Role(String name){
        this.name = name;
    }

    public void clone(RoleRequest data){
        this.name = data.getName();
    }
}
