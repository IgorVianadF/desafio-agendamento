package com.desafio.agendamento_telefonico.exceptions;

public class ContactAlreadyExistsException extends RuntimeException {
    public ContactAlreadyExistsException(String message) {
        super(message);
    }
}
