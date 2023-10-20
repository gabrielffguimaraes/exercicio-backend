package com.br.exerciciobackend.services;

import com.br.exerciciobackend.dto.UserDto;
import com.br.exerciciobackend.enums.UserType;
import com.br.exerciciobackend.model.User;
import com.br.exerciciobackend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateUserTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Logistas não estão autorizados a realizar transação");
        }
        if(sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente");
        }
    }
    public User findUserById(Long id) throws Exception{
        return this.userRepository.findUserById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }
    public User createUser(UserDto userDto) {
        User user = new User(userDto);
        return this.saveUser(user);
    }
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }
}
