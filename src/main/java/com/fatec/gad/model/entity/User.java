package com.fatec.gad.model.entity;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {
    @Id @GeneratedValue
    private Long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @OneToOne @JoinColumn
    private UserPersonalData userPersonalData;

    @OneToOne @JoinColumn
    private UserContact userContact;

    @ManyToMany(cascade = CascadeType.ALL )
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column
    private List<Role> roles;
}
