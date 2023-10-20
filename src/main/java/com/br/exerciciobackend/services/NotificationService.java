package com.br.exerciciobackend.services;

import com.br.exerciciobackend.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;
    public void notificateUser(User user,String message) throws Exception {
        Map<String,String> body = new HashMap<>();
        body.put("user",user.getId().toString());
        body.put("message", message);

        ResponseEntity<String> response = this.restTemplate.getForEntity("https://mockbin.org/bin/cda55e73-39d5-4d10-bc62-6f49dfc80b7b",String.class, body );
        String responseMessage = (String) new ObjectMapper().readValue(response.getBody(),Map.class).get("message");
        if(response.getStatusCode() != HttpStatus.OK || !responseMessage.equals("Email enviado com sucesso .")) {
            System.out.println("erro ao enviar mensagem user: " + user.getId());
            throw new Exception("Erro ao enviar mensagem serviço de notificação fora do ar .");
        }
    };
}
