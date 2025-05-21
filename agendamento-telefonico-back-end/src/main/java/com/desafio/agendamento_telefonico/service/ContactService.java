package com.desafio.agendamento_telefonico.service;

import com.desafio.agendamento_telefonico.DTO.request.ContactRegisterRequestDTO;
import com.desafio.agendamento_telefonico.entity.Contact;
import com.desafio.agendamento_telefonico.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.desafio.agendamento_telefonico.mapper.Mapper.getContact;
import static com.desafio.agendamento_telefonico.mapper.Mapper.getCreationContact;


@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    public List<Contact> getAllContacts(){
     return contactRepository.findAll();
    }

    public Contact getContactById(int id){
        return contactRepository.findById(id).orElse(null);
    }

    public ResponseEntity<?> favoriteContact(int id){
        try{
            Contact contato = contactRepository.findById(id).orElse(null);
            if(contato!=null){
                contato.setContatoSnFavorito(contato.getContatoSnFavorito()=='s'?'n':'s');
                contactRepository.save(contato);
                return ResponseEntity.status(HttpStatus.OK).build();
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> inactivateContact(int id){
        try{
            Contact contato = contactRepository.findById(id).orElse(null);
            if(contato!=null){
                contato.setContatoSnAtivo(contato.getContatoSnAtivo()=='s'?'n':'s');
                contactRepository.save(contato);
                return ResponseEntity.status(HttpStatus.OK).build();
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> updateContact(int id, ContactRegisterRequestDTO updatedContact){
        try {
        boolean exists = contactRepository.existsById(id);
        if(exists){
            Contact contact = getContact(id, updatedContact);
            contactRepository.save(contact);
            return ResponseEntity.status(HttpStatus.OK).build();
            }
        }catch (DataIntegrityViolationException e){
            if (e.getMessage() != null && e.getMessage().contains("unique_contato_celular")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<?> createContact(ContactRegisterRequestDTO newContact){
        Contact contact = getCreationContact(newContact);
        try {
            contactRepository.save(contact);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception error){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
