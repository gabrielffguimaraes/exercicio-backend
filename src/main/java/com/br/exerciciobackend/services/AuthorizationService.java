package com.br.exerciciobackend.services;

import com.br.exerciciobackend.model.User;
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

    public boolean authorizeTransaction(User sender, BigDecimal value) {
        String url = "http://mockbin.org/bin/6015df33-38d7-4ca5-9f0e-728cded46268?foo=bar&foo=baz";
        ResponseEntity<Map> responseTransaction = this.restTemplate.getForEntity(url,Map.class);
        if(responseTransaction.getStatusCode() == HttpStatus.OK) {
            String message = (String) responseTransaction.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else return false;


    }
}
