package com.br.exerciciobackend.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestControllerAdvicer {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> treatDuplicate(DataIntegrityViolationException ex) {
        Map<String,String> map = new HashMap<>();
        map.put("message","Violação de dados unicos .");
        map.put("status","400");
        return new ResponseEntity<Map>(map,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> treat404(EntityNotFoundException ex) {
        Map<String,String> map = new HashMap<>();
        map.put("message",ex.getMessage());
        map.put("status","404");
        return new ResponseEntity<Map>(map,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> tratativaErrosSistema(Exception ex) {
        Map<String,String> map = new HashMap<>();
        map.put("message",ex.getMessage());
        map.put("status","400");
        return new ResponseEntity<Map>(map,HttpStatus.BAD_REQUEST);
    }
}
