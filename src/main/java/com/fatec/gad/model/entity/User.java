package com.fatec.gad.model.entity;

import com.fatec.gad.model.request.UserRequest;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
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
    @ToString.Exclude
    private List<Role> roles;

    public void clone(UserRequest data){
        this.username = data.getUsername();
        this.email = data.getEmail();
        this.password = data.getPassword();
    }
}
