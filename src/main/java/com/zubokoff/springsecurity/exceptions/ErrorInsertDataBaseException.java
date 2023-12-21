package com.zubokoff.springsecurity.exceptions;

public class ErrorInsertDataBaseException extends RuntimeException {
    public ErrorInsertDataBaseException() {
        super("Erro ao inserir no Banco de dados");
    }
}
