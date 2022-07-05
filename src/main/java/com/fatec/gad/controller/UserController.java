package com.fatec.gad.controller;

import com.fatec.gad.exception.InvalidRegisterException;
import com.fatec.gad.model.request.UserRequest;
import com.fatec.gad.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/gaad")
public class UserController {
    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest userRequest){
        logger.debug("Start register new user");
        try {
            userService.register(userRequest);
            logger.info("New user was registered");
            return ResponseEntity.ok("New user was registered.");
        } catch (InvalidRegisterException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("Register had errors");
        }
    }
}
