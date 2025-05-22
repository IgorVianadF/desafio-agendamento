package com.desafio.agendamento_telefonico.service;

import com.desafio.agendamento_telefonico.dto.contact.ContactDTO;
import com.desafio.agendamento_telefonico.dto.contact.SimpleContactDTO;
import com.desafio.agendamento_telefonico.entity.Contact;
import com.desafio.agendamento_telefonico.exceptions.ContactNotFoundException;
import com.desafio.agendamento_telefonico.repository.ContactRepository;
import com.desafio.agendamento_telefonico.validation.ContactValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.desafio.agendamento_telefonico.mapper.ContactMapper.*;


@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;

    @Autowired
    ContactValidation contactValidation;

    public List<ContactDTO> getAllContacts(){
     List<Contact> contactList = contactRepository.findAll();
     List<ContactDTO> contactDtoList = new ArrayList<>(){
     };
     for(Contact contact : contactList){
          contactDtoList.add(toContactDto(contact));
     }
        return contactDtoList;
    }

    public ContactDTO getContactById(Long id){
        Contact contact = contactRepository.findById(id).orElseThrow(()-> new ContactNotFoundException("Contato não encontrado"));
        return toContactDto(contact);
    }

    public SimpleContactDTO favoriteContact(Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(()->new ContactNotFoundException("Contato não encontrado"));
            contact.setContatoSnFavorito(contact.getContatoSnFavorito() == 's' ? 'n' : 's');
            contactRepository.save(contact);
            return toSimpleContactDto(contact);
    }

    public SimpleContactDTO inactivateContact(Long id){
            Contact contact = contactRepository.findById(id).orElseThrow(()-> new ContactNotFoundException("Contato não encontrado"));
                contact.setContatoSnAtivo(contact.getContatoSnAtivo()=='s'?'n':'s');
                contactRepository.save(contact);
                return toSimpleContactDto(contact);
    }

    public SimpleContactDTO updateContact(Long id, SimpleContactDTO updatedContact) {
        Contact contact = toContact(id, updatedContact);
        contactValidation.celularAlreadyExists(contact, true);
        contactRepository.save(contact);
        return toSimpleContactDto(contact);
    }

    public SimpleContactDTO createContact(SimpleContactDTO newContact){
        Contact contact = toContactWithoutId(newContact);
        contactValidation.celularAlreadyExists(contact, false);
        contactRepository.save(contact);
        return toSimpleContactDto(contact);
    }

    public void deleteContact(Long id){
        contactRepository.deleteById(id);
    }
}
