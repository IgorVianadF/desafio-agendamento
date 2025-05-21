package com.desafio.agendamento_telefonico.mapper;

import com.desafio.agendamento_telefonico.DTO.request.ContactRegisterRequestDTO;
import com.desafio.agendamento_telefonico.entity.Contact;

public class Mapper {
    public static Contact getContact(int id, ContactRegisterRequestDTO updatedContact) {
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

    public static Contact getCreationContact(ContactRegisterRequestDTO updatedContact) {
        Contact contact = new Contact();
        contact.setContatoNome(updatedContact.contatoNome());
        contact.setContatoEmail(updatedContact.contatoEmail());
        contact.setContatoCelular(updatedContact.contatoCelular());
        contact.setContatoTelefone(updatedContact.contatoTelefone());
        contact.setContatoSnFavorito(updatedContact.contatoSnFavorito());
        contact.setContatoSnAtivo(updatedContact.contatoSnAtivo());
        return contact;
    }
}
