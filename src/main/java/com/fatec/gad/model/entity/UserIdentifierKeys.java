package com.fatec.gad.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
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
