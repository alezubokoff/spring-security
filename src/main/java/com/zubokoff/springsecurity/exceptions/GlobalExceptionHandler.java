package com.zubokoff.springsecurity.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ErrorInsertDataBaseException.class)
    ProblemDetail handlerErrorInsertDataBaseException(ErrorInsertDataBaseException e) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        detail.setTitle("Erro ao tentar inserir dados");
        detail.setDetail("Erro ao tentar inserir os valores informados no Bando de dados");
        return detail;
    }
}
