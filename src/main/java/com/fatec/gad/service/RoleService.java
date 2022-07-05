package com.fatec.gad.service;

import com.fatec.gad.dao.repository.RoleRepository;
import com.fatec.gad.model.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public Role getRole(String name){
        Role role = roleRepository.findByName(name);
        if(role == null){
            role = createRole(name);
        }
        return role;
    }

    private Role createRole(String name){
        Role role = new Role(name);
        roleRepository.save(role);
        return role;
    }
}
