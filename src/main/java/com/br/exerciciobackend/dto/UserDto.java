package com.br.exerciciobackend.dto;

import java.math.BigDecimal;


public record UserDto (
        String firstName,
        String lastName,
        String document,
        BigDecimal amount,
        String email

) {

}
