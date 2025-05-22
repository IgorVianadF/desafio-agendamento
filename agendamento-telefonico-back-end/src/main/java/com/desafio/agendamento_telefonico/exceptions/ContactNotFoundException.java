package com.desafio.agendamento_telefonico.exceptions;

public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(String message) {
        super(message);
    }
}
