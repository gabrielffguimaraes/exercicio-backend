package com.br.exerciciobackend.services;

import com.br.exerciciobackend.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class AuthorizationService {

    @Autowired
    private RestTemplate restTemplate;

    public boolean authorizeTransaction(User sender, BigDecimal value) throws JsonProcessingException {
        String url = "http://mockbin.org/bin/6015df33-38d7-4ca5-9f0e-728cded46268?foo=bar&foo=baz";
        ResponseEntity<String> responseTransaction = this.restTemplate.getForEntity(url,String.class);
        if(responseTransaction.getStatusCode() == HttpStatus.OK) {
            String message = (String) new ObjectMapper()
                    .readValue(responseTransaction.getBody(),Map.class)
                    .get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else return false;
    }
}
