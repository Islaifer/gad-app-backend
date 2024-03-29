package com.fatec.gad.service;

import com.fatec.gad.constants.RolesConstant;
import com.fatec.gad.dao.repository.UserContactRepository;
import com.fatec.gad.dao.repository.UserIdentifierKeysRepository;
import com.fatec.gad.dao.repository.UserPersonalDataRepository;
import com.fatec.gad.dao.repository.UserRepository;
import com.fatec.gad.exception.InvalidCrmException;
import com.fatec.gad.exception.InvalidRegisterException;
import com.fatec.gad.model.entity.*;
import com.fatec.gad.model.request.UserRequest;
import com.fatec.gad.security.util.CryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final UserPersonalDataRepository userPersonalDataRepository;

    private final UserContactRepository userContactRepository;

    private final UserIdentifierKeysRepository userIdentifierKeysRepository;

    private final MedicService medicService;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository, UserPersonalDataRepository userPersonalDataRepository,
                       UserContactRepository userContactRepository, RoleService roleService,
                       MedicService medicService, UserIdentifierKeysRepository userIdentifierKeysRepository){
        this.userRepository = userRepository;
        this.userPersonalDataRepository = userPersonalDataRepository;
        this.userContactRepository = userContactRepository;
        this.roleService = roleService;
        this.medicService = medicService;
        this.userIdentifierKeysRepository = userIdentifierKeysRepository;
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

    public void register(UserRequest request) throws InvalidRegisterException, InvalidCrmException {
        logger.debug("Start register");
        User user;
        UserIdentifierKeys userIdentifierKeys;
        try {
            logger.debug("Validating user");
            validUser(request);
            user = new User();
            user.clone(request);
            logger.debug("Crypt password");
            user.setPassword(CryptUtil.encryptPassword(user.getPassword()));
            logger.debug("Create personal data");
            user.setUserPersonalData(registerPersonalData(request));
            logger.debug("Create contact");
            user.setUserContact(registerContact(request));
            logger.debug("Create roles");
            user.setRoles(createRoles(request));
            logger.debug("Saving new user");
            userRepository.save(user);
            logger.debug("Create key identifier");
            userIdentifierKeys = new UserIdentifierKeys();
            userIdentifierKeys.setUser(user);
            userIdentifierKeys.setIdentifyKey(generateKey(user));
            userIdentifierKeysRepository.save(userIdentifierKeys);
            logger.info("User saved successfully");
        } catch (InvalidRegisterException | InvalidCrmException e) {
            logger.error("Invalid Register error!");
            logger.error(e.getMessage());
            throw e;
        }
    }

    /*-------------------------Utils--------------------------*/

    private UserPersonalData registerPersonalData(UserRequest request) throws InvalidCrmException {
        UserPersonalData data = new UserPersonalData();
        if(request.getUserPersonalDataRequest().getCrm() != null &&
        !request.getUserPersonalDataRequest().getCrm().isEmpty()){
            logger.debug("Validating crm");
            medicService.validCrm(request.getUserPersonalDataRequest().getCrm(),
                    request.getUserContactRequest().getUf());
        }
        data.clone(request.getUserPersonalDataRequest());
        userPersonalDataRepository.save(data);
        logger.debug("Personal Data had been saved");
        return data;
    }

    private UserContact registerContact(UserRequest request){
        UserContact data = new UserContact();
        data.clone(request.getUserContactRequest());
        userContactRepository.save(data);
        return data;
    }

    private List<Role> createRoles(UserRequest userRequest){
        Role roleUser;
        Role roleMedic;
        List<Role> roles = new LinkedList<>();
        roleUser = roleService.getRole(RolesConstant.USER.toString());
        roles.add(roleUser);
        if((userRequest.getUserPersonalDataRequest().getCrm() != null &&
                !userRequest.getUserPersonalDataRequest().getCrm().isEmpty())){
            roleMedic = roleService.getRole(RolesConstant.MEDIC.toString());
            roles.add(roleMedic);
        }
        return roles;
    }

    private void validUser(UserRequest request) throws InvalidRegisterException {
        if(request.getUsername().length() < 4){
            logger.error("Username needs to have 4 character in minimal!");
            throw new InvalidRegisterException("Username needs to have 4 character in minimal!");
        }
        if(!request.getEmail().contains("@")){
            logger.error("Invalid Email");
            throw new InvalidRegisterException("Invalid Email");
        }
        if(request.getPassword().length() < 8){
            logger.error("Password needs to have 8 character in minimal!");
            throw new InvalidRegisterException("Password needs to have 8 character in minimal!");
        }
        if(!request.getPassword().equals(request.getConfirmPassword())){
            logger.error("Passwords don't coincides");
            throw new InvalidRegisterException("Passwords don't coincides");
        }
        validExistsUser(request.getUsername(), request.getEmail());
        validExistsPersonalData(request.getUserPersonalDataRequest().getCpf(),
                request.getUserPersonalDataRequest().getRg(),
                request.getUserPersonalDataRequest().getCrm());
    }

    private void validExistsUser(String name, String email) throws InvalidRegisterException {
        User user;
        user = userRepository.findByUsername(name);
        if(user != null){
            logger.error("User already exists by username: ".concat(name));
            throw new InvalidRegisterException("User already exists by username: ".concat(name));
        }
        user = userRepository.findByEmail(email);
        if(user != null){
            logger.error("User already exists by email: ".concat(email));
            throw new InvalidRegisterException("User already exists by email: ".concat(email));
        }
    }

    private void validExistsPersonalData(String cpf, String rg, String crm)
            throws InvalidRegisterException {
        UserPersonalData user;
        user = userPersonalDataRepository.findByCpf(cpf);
        if(user != null){
            logger.error("User already exists by cpf: ".concat(cpf));
            throw new InvalidRegisterException("User already exists by cpf: ".concat(cpf));
        }
        user = userPersonalDataRepository.findByRg(rg);
        if(user != null){
            logger.error("User already exists by rg: ".concat(rg));
            throw new InvalidRegisterException("User already exists by rg: ".concat(rg));
        }
        if(crm != null && !crm.isEmpty()){
            user = userPersonalDataRepository.findByCrm(crm);
            if(user != null){
                logger.error("User already exists by crm: ".concat(crm));
                throw new InvalidRegisterException("User already exists by crm: ".concat(crm));
            }
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

    private String generateKey(User user){
        int xDec;
        StringBuilder returnedKey = new StringBuilder();
        char [] key = user.getUsername().toCharArray();
        for(char var : key){
            xDec = var;
            returnedKey.append(xDec);
        }
        return returnedKey.toString();
    }
}
