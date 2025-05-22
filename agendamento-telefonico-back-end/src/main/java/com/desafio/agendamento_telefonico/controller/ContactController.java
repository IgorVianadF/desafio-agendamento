package com.desafio.agendamento_telefonico.controller;

import com.desafio.agendamento_telefonico.dto.contact.ContactDTO;
import com.desafio.agendamento_telefonico.dto.contact.SimpleContactDTO;
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
    public ResponseEntity<List<ContactDTO>> getAll(){
        return ResponseEntity.ok(contactService.getAllContacts());
    }

    @PostMapping
    public ResponseEntity<SimpleContactDTO> createContact(@RequestBody SimpleContactDTO newContact){
        return ResponseEntity.ok(contactService.createContact(newContact));
    }

    @GetMapping("/{id}")
    public ContactDTO getContact(@PathVariable Long id){
        return contactService.getContactById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SimpleContactDTO> updateContact(@PathVariable Long id, @RequestBody SimpleContactDTO updatedContact){
        return ResponseEntity.ok(contactService.updateContact(id, updatedContact));
    }

    @PatchMapping("/{id}/favoritar")
    public SimpleContactDTO favoriteContact(@PathVariable Long id){
        return contactService.favoriteContact(id);
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<SimpleContactDTO> inactivateContact(@PathVariable Long id){
        return ResponseEntity.ok(contactService.inactivateContact(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id){
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}
