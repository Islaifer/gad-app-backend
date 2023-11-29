package com.fatec.gad.controller;

import com.fatec.gad.model.request.SickRequest;
import com.fatec.gad.model.request.UserRequest;
import com.fatec.gad.service.SickService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/gaad/sick")
public class SickController {


    private final SickService service;

    private final Logger logger = LoggerFactory.getLogger(SickController.class);

    @Autowired
    public SickController(SickService service){
        this.service = service;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/post")
    public ResponseEntity<String> post(@AuthenticationPrincipal UserRequest user,
                                       @RequestBody SickRequest request){
        try{
            logger.debug("Start to post new Sick");
            service.post(user, request);
            return ResponseEntity.ok("New Sick had been add");
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("New Sick hadn't been add");
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/getAll")
    public ResponseEntity<List<SickRequest>> getAll(@AuthenticationPrincipal UserRequest user){
        try{
            logger.debug("Start to get Sick");
            return ResponseEntity.ok(service.getAll(user));
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/delete")
    public ResponseEntity<String> delete(@AuthenticationPrincipal UserRequest user,
                                       @RequestBody SickRequest request){
        try{
            logger.debug("Start to delete Sick");
            service.delete(user, request);
            return ResponseEntity.ok("Sick had been deleted");
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("Sick hadn't been deleted");
        }
    }
}
