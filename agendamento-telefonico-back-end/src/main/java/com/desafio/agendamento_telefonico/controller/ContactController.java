package com.desafio.agendamento_telefonico.controller;

import com.desafio.agendamento_telefonico.DTO.request.ContactRegisterRequestDTO;
import com.desafio.agendamento_telefonico.entity.Contact;
import com.desafio.agendamento_telefonico.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    @Autowired
    ContactService contactService;

    @GetMapping
    public List<Contact> getAll(){
        return contactService.getAllContacts();
    }

    @PostMapping
    public ResponseEntity<?> saveContact(@RequestBody ContactRegisterRequestDTO newContact){
        return contactService.createContact(newContact);
    }

    @GetMapping("/{id}")
    public Contact getContact(@PathVariable int id){
        return contactService.getContactById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(@PathVariable int id, @RequestBody ContactRegisterRequestDTO updatedContact){
        return contactService.updateContact(id, updatedContact);
    }

    @PatchMapping("/{id}/favoritar")
    public ResponseEntity<?> favoriteContact(@PathVariable int id){
        return contactService.favoriteContact(id);
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<?> inactivateContact(@PathVariable int id){
        return contactService.inactivateContact(id);
    }
}
