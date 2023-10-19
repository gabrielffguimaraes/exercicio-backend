package com.br.exerciciobackend.repository;

import com.br.exerciciobackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByCpf(String document);
    Optional<User> findUserById(Long document);
}
