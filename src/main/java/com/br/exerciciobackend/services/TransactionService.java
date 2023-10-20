package com.br.exerciciobackend.services;

import com.br.exerciciobackend.dto.TransactionDto;
import com.br.exerciciobackend.model.Transaction;
import com.br.exerciciobackend.model.User;
import com.br.exerciciobackend.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {
    private final AuthorizationService authorizationService;
    private final UserService userService;

    private final NotificationService notificationService;
    private final TransactionRepository transactionRepository;


    public TransactionService(AuthorizationService authorizationService, UserService userService, NotificationService notificationService, TransactionRepository transactionRepository) {
        this.authorizationService = authorizationService;
        this.userService = userService;
        this.notificationService = notificationService;
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(TransactionDto transactionDto) throws Exception {
        User sender = this.userService.findUserById(transactionDto.senderId());
        User receiver = this.userService.findUserById(transactionDto.receiverId());

        userService.validateUserTransaction(sender,transactionDto.value());

        boolean isAuthorized = this.authorizationService.authorizeTransaction(sender,transactionDto.value());
        if(!isAuthorized) {
            throw new Exception("Transação não autorizada");
        }
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transactionDto.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionDto.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDto.value()));

        transactionRepository.save(newTransaction);

        this.notificationService.notificateUser(sender,"Transação enviada com sucesso !");
        this.notificationService.notificateUser(receiver,"Transação recebida com sucesso !");

        return newTransaction;
    }
}
