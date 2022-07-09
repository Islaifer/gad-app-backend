package com.fatec.gad.service;

import com.fatec.gad.dao.repository.UserPersonalDataRepository;
import com.fatec.gad.dao.repository.UserRepository;
import com.fatec.gad.exception.InvalidUserException;
import com.fatec.gad.model.entity.User;
import com.fatec.gad.model.entity.UserPersonalData;
import com.fatec.gad.model.request.UserPersonalDataRequest;
import com.fatec.gad.model.request.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPersonalDataService {

    private final UserRepository userRepository;

    private final UserPersonalDataRepository userPersonalDataRepository;

    private final Logger logger = LoggerFactory.getLogger(UserPersonalDataService.class);

    @Autowired
    public UserPersonalDataService(UserRepository userRepository,
                                   UserPersonalDataRepository userPersonalDataRepository){
        this.userRepository = userRepository;
        this.userPersonalDataRepository = userPersonalDataRepository;
    }

    public UserPersonalDataRequest get(UserRequest userRequest){
        User user = getUser(userRequest);
        if(user == null){
            logger.error("Data not found");
            return null;
        }
        UserPersonalDataRequest data = new UserPersonalDataRequest();
        data.clone(user.getUserPersonalData());
        logger.info("Data found");
        return data;
    }

    public void update(UserRequest userRequest, UserPersonalDataRequest request) throws InvalidUserException {
        User user = getUser(userRequest);
        if(user == null){
            logger.error("Data not found");
            throw new InvalidUserException("Data not found");
        }
        UserPersonalData userPersonalData = user.getUserPersonalData();
        userPersonalData.clone(request);
        userPersonalDataRepository.save(userPersonalData);
        logger.info("Data updated");
    }

    private User getUser(UserRequest userRequest){
        return  userRepository.findByUsername(userRequest.getUsername());
    }
}
