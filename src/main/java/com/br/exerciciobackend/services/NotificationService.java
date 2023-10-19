package com.br.exerciciobackend.services;

import com.br.exerciciobackend.model.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class NotificationService {
    public void notificateUser(User sender,User receiver, BigDecimal amount) {
        System.out.printf("Valor de %.2f enviado para o usuario %s %s",amount,receiver.getFirstName(),receiver.getLastName());
    };
}
