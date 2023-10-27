package com.br.exerciciobackend.services;

import com.br.exerciciobackend.dto.TransactionDto;
import com.br.exerciciobackend.enums.UserType;
import com.br.exerciciobackend.model.User;
import com.br.exerciciobackend.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;


class TransactionServiceTest {

    @Mock
    AuthorizationService authorizationService;
    @Mock
    UserService userService;
    @Mock
    NotificationService notificationService;
    @Mock
    TransactionRepository transactionRepository;

    @Autowired
    @InjectMocks
    TransactionService transactionService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("should create transaction successfully when everything is ok")
    void createTransactionCase1() throws Exception {
        User sender = new User(1L,"Gabriel","Ferreira","15224280702","gabrielffguimaraes@gmail.com","24041254",new BigDecimal(10), UserType.COMMON);
        User receiver = new User(2L,"Carlos","Oliveira","8770978872","carlos@gmail.com","24041254",new BigDecimal(10), UserType.MERCHANT);
        Mockito.when(userService.findUserById(1L)).thenReturn(sender);
        Mockito.when(userService.findUserById(2L)).thenReturn(receiver);

        Mockito.when(authorizationService.authorizeTransaction(any() , any())).thenReturn(true);
        TransactionDto transactionDto = new TransactionDto(new BigDecimal(10),1L,2L);
        transactionService.createTransaction(transactionDto);

        verify(transactionRepository,times(1)).save(any());
        verify(notificationService,times(1)).notificateUser(eq(sender),anyString());
        verify(notificationService,times(1)).notificateUser(eq(receiver),anyString());
        verify(notificationService,times(2)).notificateUser(any(),anyString());

        verify(userService,times(1)).saveUser(sender);
        verify(userService,times(1)).saveUser(receiver);

        assertThat(sender.getBalance()).isEqualTo(new BigDecimal(0));
        assertThat(receiver.getBalance()).isEqualTo(new BigDecimal(20));
    }
    @Test
    @DisplayName("should throw exception when transaction is not allowed")
    void createTransactionCase2() throws Exception {
        User sender = new User(1L,"Gabriel","Ferreira","15224280702","gabrielffguimaraes@gmail.com","24041254",new BigDecimal(10), UserType.COMMON);
        User receiver = new User(2L,"Carlos","Oliveira","8770978872","carlos@gmail.com","24041254",new BigDecimal(10), UserType.MERCHANT);
        Mockito.when(userService.findUserById(1L)).thenReturn(sender);
        Mockito.when(userService.findUserById(2L)).thenReturn(receiver);

        Mockito.when(authorizationService.authorizeTransaction(any() , any())).thenReturn(false);

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            TransactionDto transactionDto = new TransactionDto(new BigDecimal(10),1L,2L);
            transactionService.createTransaction(transactionDto);
        });
        assertThat(exception.getMessage()).isEqualTo("Transação não autorizada");
    }
}