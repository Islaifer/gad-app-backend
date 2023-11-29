package com.fatec.gad.service;

import com.fatec.gad.dao.repository.SickRepository;
import com.fatec.gad.dao.repository.UserRepository;
import com.fatec.gad.exception.InvalidUserException;
import com.fatec.gad.model.entity.EmergencyContact;
import com.fatec.gad.model.entity.Sick;
import com.fatec.gad.model.entity.User;
import com.fatec.gad.model.request.SickRequest;
import com.fatec.gad.model.request.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class SickService {

    private final UserRepository userRepository;

    private final SickRepository sickRepository;

    private final Logger logger = LoggerFactory.getLogger(SickService.class);

    @Autowired
    public SickService(UserRepository userRepository,
                       SickRepository sickRepository){
        this.userRepository = userRepository;
        this.sickRepository = sickRepository;
    }

    public void post(UserRequest user, SickRequest request){
        User userEntity = userRepository.findByUsername(user.getUsername());
        Sick sick = new Sick();
        sick.setUserPersonalData(userEntity.getUserPersonalData());
        sick.clone(request);
        logger.debug("Start to save new sick");
        sickRepository.save(sick);
        logger.info("New sick has been saved");
    }

    public List<SickRequest> getAll(UserRequest user){
        SickRequest sickRequest;
        List<SickRequest> sickRequests = new LinkedList<>();
        List<Sick> sicks = userRepository.findByUsername(user.getUsername()).getUserPersonalData().getSicks();
        for(Sick sick : sicks){
            sickRequest = new SickRequest();
            sickRequest.clone(sick);
            sickRequests.add(sickRequest);
        }
        return sickRequests;
    }

    public void delete(UserRequest userRequest, SickRequest data) throws ChangeSetPersister.NotFoundException {
        Sick sick = sickRepository.findById(data.getId()).orElse(null);

        if(sick == null)
            throw new ChangeSetPersister.NotFoundException();

        sickRepository.delete(sick);
    }
}
