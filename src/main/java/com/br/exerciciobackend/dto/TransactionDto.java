package com.br.exerciciobackend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


public record TransactionDto(
        BigDecimal value,
        Long senderId,
        Long receiverId
) {

}
