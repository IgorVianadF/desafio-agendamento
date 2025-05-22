package com.desafio.agendamento_telefonico.validation;

import com.desafio.agendamento_telefonico.entity.Contact;
import com.desafio.agendamento_telefonico.exceptions.ContactAlreadyExistsException;
import com.desafio.agendamento_telefonico.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactValidation {

    @Autowired
    ContactRepository contactRepository;

    public void celularAlreadyExists(Contact contact, boolean update){
        boolean exists = update ? contactRepository.existsByContatoCelularAndContatoIdIsNot(contact.getContatoCelular(), contact.getContatoId()) : contactRepository.existsByContatoCelular(contact.getContatoCelular());
        if(exists){
            throw new ContactAlreadyExistsException("Contato já existe!");
        }
    }

}
