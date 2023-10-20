package com.br.exerciciobackend.model;

import com.br.exerciciobackend.dto.UserDto;
import com.br.exerciciobackend.enums.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of="id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true,nullable = false)
    private String cpf;
    @Column(unique = true,nullable = false)
    private String email;
    private String password;
    @Column(nullable = false)
    private BigDecimal balance;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(UserDto userDto) {
        this.firstName = userDto.firstName();
        this.lastName = userDto.lastName();
        this.cpf = userDto.document();
        this.balance = userDto.balance();
        this.userType = userDto.userType();
        this.email = userDto.email();
        this.password = userDto.password();
    }
}
