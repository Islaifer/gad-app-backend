package com.fatec.gad.service;

import com.fatec.gad.constants.MedicConstant;
import com.fatec.gad.exception.InvalidCrmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class MedicService {
    private final Logger logger = LoggerFactory.getLogger(MedicService.class);

    @Value("${crm.key}")
    private String key;

    public void validCrm(String crm, String uf) throws InvalidCrmException {
        logger.debug("Validating crm: ".concat(crm));
        String url = MedicConstant.URL_CRM_PREFIX.concat(uf).concat(MedicConstant.URL_CRM_CRM)
                .concat(crm).concat(MedicConstant.URL_CRM_MIDDLE)
                .concat(key).concat(MedicConstant.URL_CRM_SUFFIX);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity;
        headers.add("user-agent", "Application");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        entity = new HttpEntity<>(headers);
        result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        if(result.getBody() == null || !result.getBody().contains("\"situacao\":\"Ativo\"")){
            logger.error("Invalid CRM");
            throw new InvalidCrmException("Invalid CRM");
        }
        logger.info("Valid CRM");
    }
}
