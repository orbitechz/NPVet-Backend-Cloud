package com.orbitech.npvet.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.net.http.HttpClient;
import java.nio.file.AccessDeniedException;
import java.security.InvalidKeyException;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {
    /**
     * Erros de Service
     * */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        log.error("OCORREU UM ERRO Exception: " + e.getMessage());
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDenied(AccessDeniedException e) {
        return e.getMessage();
    }
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public String handleNotFound(HttpClientErrorException e) {
        log.error("OCORREU UM ERRO HttpClientErrorException:" + e.getMessage());
        return "Ocorreu um erro interno!";
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public String handleUnauthorized() {
        return "Login inválido!";
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InvalidKeyException.class)
    public String handleInvalidKey(InvalidKeyException e) {
        log.error("OCORREU UM ERRO InvalidKeyException: " + e.getMessage());
        return "Ocorreu um erro interno!";
    }

    /**
     * Erros do Hibernate Validator
     * */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String  handleValidationException(
            final MethodArgumentNotValidException methodArgumentNotValidException
    ){
        List<ObjectError> errors = methodArgumentNotValidException.getBindingResult().getAllErrors();
        FieldError firstError = (FieldError) errors.get(0);
        log.error("OCORREU UM ERRO MethodArgumentNotValidException:" + firstError.getDefaultMessage());
        return firstError.getDefaultMessage();
    }

//    /**
//     * Erros de sintaxe no body
//     * */
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public String handleJsonException(){
//        return "Existem erros na sua solicitação!";
//    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServiceException.class)
    public String handleServiceException(ServiceException e){
        log.error("OCORREU UM ERRO ServiceException:  " + e.getMessage());
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(Exception e){
        log.error("OCORREU UM ERRO IllegalArgumentException: " + e.getMessage());
        return e.getMessage();
    }

}
