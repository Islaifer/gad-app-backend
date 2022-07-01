package com.fatec.gad.service;

import com.fatec.gad.dao.repository.UserRepository;
import com.fatec.gad.model.entity.User;
import com.fatec.gad.model.request.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        logger.debug("Start login");
        try{
            return get(login);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

    private UserRequest get(String login) throws UsernameNotFoundException{
        logger.debug("Start catching user");
        User user;
        try{
            logger.info("Try get user by username");
            user = userRepository.findByUsername(login);
            if(user == null){
                logger.warn("Username not found. Trying get user by email");
                user = userRepository.findByEmail(login);
                if(user == null){
                    logger.error("User wasn't found!");
                    throw new UsernameNotFoundException("User wasn't found!");
                }
            }
            logger.info("User has been found by Username/Password: ".concat(login));
            return UserRequest.create(user);
        }catch (Exception e){
            logger.error("User wasn't found!");
            throw new UsernameNotFoundException("User wasn't found!");
        }
    }
}
