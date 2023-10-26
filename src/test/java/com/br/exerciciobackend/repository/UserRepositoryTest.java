package com.br.exerciciobackend.repository;

import com.br.exerciciobackend.dto.UserDto;
import com.br.exerciciobackend.enums.UserType;
import com.br.exerciciobackend.model.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;


@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("should get user successfully from DB")
    void findUserByCpfCase1() {
        String cpf = "15224280702";
        UserDto data = new UserDto("Gabriel",
                "Ferreira",
                cpf,
                new BigDecimal(10),
                "gabrielferreira@gmail.com",
                "999",
                UserType.MERCHANT);

        this.createUser(data);

        Optional<User> result = this.userRepository.findUserByCpf(cpf);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("should not get user successfully from DB")
    void findUserByCpfCase2() {
        String cpf = "15224280702";
        Optional<User> result = this.userRepository.findUserByCpf(cpf);
        assertThat(result.isEmpty()).isTrue();
    }

    private User createUser(UserDto data) {
        User newUser = new User(data);
        entityManager.persist(newUser);
        return newUser;
    }
}
