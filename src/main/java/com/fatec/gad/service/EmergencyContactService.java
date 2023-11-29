package com.fatec.gad.service;

import com.fatec.gad.dao.repository.EmergencyContactRepository;
import com.fatec.gad.dao.repository.UserRepository;
import com.fatec.gad.exception.InvalidRegisterException;
import com.fatec.gad.exception.InvalidUserException;
import com.fatec.gad.model.entity.EmergencyContact;
import com.fatec.gad.model.entity.User;
import com.fatec.gad.model.request.EmergencyContactRequest;
import com.fatec.gad.model.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EmergencyContactService {

    private final UserRepository userRepository;

    private final EmergencyContactRepository emergencyContactRepository;

    @Autowired
    public EmergencyContactService(UserRepository userRepository, EmergencyContactRepository emergencyContactRepository){
        this.userRepository = userRepository;
        this.emergencyContactRepository = emergencyContactRepository;
    }

    public void post(UserRequest userRequest, EmergencyContactRequest data){
        User user = userRepository.findByUsername(userRequest.getUsername());
        EmergencyContact newData = new EmergencyContact();
        newData.clone(data);
        newData.setUserPersonalData(user.getUserPersonalData());
        emergencyContactRepository.save(newData);
    }

    public void update(UserRequest userRequest, EmergencyContactRequest data) throws ChangeSetPersister.NotFoundException, InvalidUserException {
        User user = userRepository.findByUsername(userRequest.getUsername());
        EmergencyContact emergencyContact = emergencyContactRepository.findById(data.getId()).orElse(null);

        if(emergencyContact == null)
            throw new ChangeSetPersister.NotFoundException();

        if(!Objects.equals(user.getId(), emergencyContact.getUserPersonalData().getId()))
            throw new InvalidUserException("Invalid operation, this emergency contact is not for this user");

        emergencyContact.clone(data);
        emergencyContact.setUserPersonalData(user.getUserPersonalData());

        emergencyContactRepository.save(emergencyContact);
    }

    public void delete(UserRequest userRequest, EmergencyContactRequest data) throws ChangeSetPersister.NotFoundException, InvalidUserException {
        User user = userRepository.findByUsername(userRequest.getUsername());
        EmergencyContact emergencyContact = emergencyContactRepository.findById(data.getId()).orElse(null);

        if(emergencyContact == null)
            throw new ChangeSetPersister.NotFoundException();

        if(!Objects.equals(user.getId(), emergencyContact.getUserPersonalData().getId()))
            throw new InvalidUserException("Invalid operation, this emergency contact is not for this user");

        emergencyContactRepository.delete(emergencyContact);
    }
}
