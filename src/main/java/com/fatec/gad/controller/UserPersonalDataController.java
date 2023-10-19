package com.fatec.gad.controller;

import com.fatec.gad.exception.InvalidUserException;
import com.fatec.gad.model.request.UserPersonalDataRequest;
import com.fatec.gad.model.request.UserRequest;
import com.fatec.gad.service.UserPersonalDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/gaad/userPersonalData")
public class UserPersonalDataController {

    private final UserPersonalDataService service;

    private final Logger logger = LoggerFactory.getLogger(UserPersonalDataController.class);

    @Autowired
    public UserPersonalDataController(UserPersonalDataService service){
        this.service = service;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get")
    public ResponseEntity<UserPersonalDataRequest> get(@AuthenticationPrincipal UserRequest userRequest){
        try{
            logger.debug("Start getting personal data");
            return ResponseEntity.ok(service.get(userRequest));
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/getSelfToken")
    public ResponseEntity<String> getSelfToken(@AuthenticationPrincipal UserRequest userRequest){
        try{
            logger.debug("Start getting self token");
            return ResponseEntity.ok(service.getSelfToken(userRequest));
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('MEDIC')")
    @GetMapping("/getByCpf/{cpf}")
    public ResponseEntity<UserPersonalDataRequest> getByUserCpf(@PathVariable("cpf") String cpf){
        try{
            logger.debug("Start get data");
            return ResponseEntity.ok(service.getByCpf(cpf));
        }catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @PreAuthorize("hasRole('MEDIC')")
    @GetMapping("/getByToken/{token}")
    public ResponseEntity<UserPersonalDataRequest> getByToken(@PathVariable("token") String token){
        try{
            logger.debug("Start get data");
            return ResponseEntity.ok(service.getByToken(token));
        }catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/put")
    public ResponseEntity<String> update(@AuthenticationPrincipal UserRequest userRequest,
                                         @RequestBody UserPersonalDataRequest request){
        try {
            logger.debug("Start update data");
            service.update(userRequest, request);
            return ResponseEntity.ok("Data was update with success");
        } catch (InvalidUserException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
