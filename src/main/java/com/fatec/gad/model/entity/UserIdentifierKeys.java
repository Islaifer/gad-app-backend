package com.fatec.gad.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserIdentifierKeys {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String identifyKey;

    @OneToOne
    @JoinColumn
    private User user;

    //insert facial and biometric keys
}
