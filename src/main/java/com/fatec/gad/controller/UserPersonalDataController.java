package com.fatec.gad.controller;

import com.fatec.gad.model.request.UserPersonalDataRequest;
import com.fatec.gad.model.request.UserRequest;
import com.fatec.gad.service.UserPersonalDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/gaad/userPersonalData")
public class UserPersonalDataController {

    private final UserPersonalDataService service;

    private final Logger logger = LoggerFactory.getLogger(UserPersonalDataController.class);

    public UserPersonalDataController(UserPersonalDataService service){
        this.service = service;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get")
    public UserPersonalDataRequest get(@AuthenticationPrincipal UserRequest userRequest){
        try{
            logger.debug("Start getting personal data");
            return service.get(userRequest);
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }
}