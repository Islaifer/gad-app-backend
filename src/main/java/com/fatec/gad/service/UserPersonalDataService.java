package com.fatec.gad.service;

import com.fatec.gad.dao.repository.UserRepository;
import com.fatec.gad.model.entity.User;
import com.fatec.gad.model.request.UserPersonalDataRequest;
import com.fatec.gad.model.request.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPersonalDataService {

    private final UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(UserPersonalDataService.class);

    @Autowired
    public UserPersonalDataService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserPersonalDataRequest get(UserRequest userRequest){
        User user = userRepository.findByUsername(userRequest.getUsername());
        if(user == null){
            logger.error("Data not found");
            return null;
        }
        UserPersonalDataRequest data = new UserPersonalDataRequest();
        data.clone(user.getUserPersonalData());
        logger.info("Data found");
        return data;
    }
}
