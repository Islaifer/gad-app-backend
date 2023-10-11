package com.fatec.gad.controller;

import com.fatec.gad.model.request.EmergencyContactRequest;
import com.fatec.gad.model.request.SickRequest;
import com.fatec.gad.model.request.UserRequest;
import com.fatec.gad.service.EmergencyContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/gaad/emergencyContact")
public class EmergencyContactController {

    private final EmergencyContactService service;

    private final Logger logger = LoggerFactory.getLogger(EmergencyContactController.class);

    @Autowired
    public EmergencyContactController(EmergencyContactService service){

        this.service = service;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/post")
    public ResponseEntity<String> post(@AuthenticationPrincipal UserRequest user,
                                       @RequestBody EmergencyContactRequest request){
        try{
            logger.debug("Start to post new Emergency Contact");
            service.post(user, request);
            return ResponseEntity.ok("New Emergency Contact had been add");
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("New Emergency Contact hadn't been add");
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/put")
    public ResponseEntity<String> update(@AuthenticationPrincipal UserRequest user,
                                       @RequestBody EmergencyContactRequest request){
        try{
            logger.debug("Start to edit Emergency Contact");
            service.update(user, request);
            return ResponseEntity.ok("Emergency Contact had been edited");
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("Emergency Contact hadn't been edited");
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/delete")
    public ResponseEntity<String> delete(@AuthenticationPrincipal UserRequest user,
                                       @RequestBody EmergencyContactRequest request){
        try{
            logger.debug("Start to delete Emergency Contact");
            service.delete(user, request);
            return ResponseEntity.ok("Emergency Contact had been deleted");
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("Emergency Contact hadn't been deleted");
        }
    }
}
