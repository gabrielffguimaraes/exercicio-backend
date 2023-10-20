package com.br.exerciciobackend.dto;

import com.br.exerciciobackend.enums.UserType;

import java.math.BigDecimal;


public record UserDto (
        String firstName,
        String lastName,
        String document,
        BigDecimal balance,
        String email,
        String password,
        UserType userType

) {

}
