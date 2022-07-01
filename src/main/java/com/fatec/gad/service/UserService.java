package com.fatec.gad.service;

import com.fatec.gad.dao.repository.UserRepository;
import com.fatec.gad.model.entity.User;
import com.fatec.gad.model.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try{
            return get(login);
        }catch (Exception e){
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

    private UserRequest get(String login) throws UsernameNotFoundException{
        User user;
        try{
            user = userRepository.findByUsername(login);
            if(user == null){
                user = userRepository.findByEmail(login);
                if(user == null){
                    throw new UsernameNotFoundException("User wasn't found!");
                }
            }
            return UserRequest.create(user);
        }catch (Exception e){
            throw new UsernameNotFoundException("User wasn't found!");
        }
    }
}
