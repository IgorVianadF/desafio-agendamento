package com.desafio.agendamento_telefonico.mapper;

import com.desafio.agendamento_telefonico.dto.contact.ContactDTO;
import com.desafio.agendamento_telefonico.dto.contact.SimpleContactDTO;
import com.desafio.agendamento_telefonico.entity.Contact;

public class ContactMapper {
    public static Contact toContact(Long id, SimpleContactDTO updatedContact) {
        Contact contact = new Contact();
        contact.setContatoId(id);
        contact.setContatoNome(updatedContact.contatoNome());
        contact.setContatoEmail(updatedContact.contatoEmail());
        contact.setContatoCelular(updatedContact.contatoCelular());
        contact.setContatoTelefone(updatedContact.contatoTelefone());
        contact.setContatoSnFavorito(updatedContact.contatoSnFavorito());
        contact.setContatoSnAtivo(updatedContact.contatoSnAtivo());
        return contact;
    }

    public static Contact toContactWithoutId(SimpleContactDTO updatedContact) {
        Contact contact = new Contact();
        contact.setContatoNome(updatedContact.contatoNome());
        contact.setContatoEmail(updatedContact.contatoEmail());
        contact.setContatoCelular(updatedContact.contatoCelular());
        contact.setContatoTelefone(updatedContact.contatoTelefone());
        contact.setContatoSnFavorito(updatedContact.contatoSnFavorito());
        contact.setContatoSnAtivo(updatedContact.contatoSnAtivo());
        return contact;
    }

    public static SimpleContactDTO toSimpleContactDto(Contact contact){
        return new SimpleContactDTO(
                contact.getContatoNome(),
                contact.getContatoEmail(),
                contact.getContatoCelular(),
                contact.getContatoTelefone(),
                contact.getContatoSnFavorito(),
                contact.getContatoSnAtivo()
        );
    }

    public static ContactDTO toContactDto(Contact contact){
        return new ContactDTO(
                contact.getContatoId(),
                contact.getContatoNome(),
                contact.getContatoEmail(),
                contact.getContatoCelular(),
                contact.getContatoTelefone(),
                contact.getContatoSnFavorito(),
                contact.getContatoSnAtivo(),
                contact.getContatoDhCad()
        );
    }
}
