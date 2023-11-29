package com.fatec.gad.security.util;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class CryptUtil {
    private CryptUtil(){
        super();
    }

    public static String encryptPassword(String pass){
        String key = BCrypt.gensalt();
        String encryptPass;
        encryptPass = BCrypt.hashpw(pass, key);
        return encryptPass;
    }

}
