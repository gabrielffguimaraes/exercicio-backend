package com.br.exerciciobackend.services;

import com.br.exerciciobackend.enums.UserType;
import com.br.exerciciobackend.model.User;
import com.br.exerciciobackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Logistas não estão autorizados a realizar transação");
        }
        if(sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente");
        }
    }
    public User findUserById(Long id) throws Exception{
        return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("User não encontrado"));
    }
    public void saveUser(User user) {
        this.userRepository.save(user);
    }
}
