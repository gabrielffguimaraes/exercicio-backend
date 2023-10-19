package com.br.exerciciobackend.repository;

import com.br.exerciciobackend.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
