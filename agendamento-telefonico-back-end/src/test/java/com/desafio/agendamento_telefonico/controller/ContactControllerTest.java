package com.desafio.agendamento_telefonico.controller;

import com.desafio.agendamento_telefonico.dto.contact.ContactDTO;
import com.desafio.agendamento_telefonico.dto.contact.SimpleContactDTO;
import com.desafio.agendamento_telefonico.entity.Contact;
import com.desafio.agendamento_telefonico.service.ContactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ContactController.class)
class ContactControllerTest {

    @Autowired
    private MockMvc contactController;

    @MockitoBean
    private ContactService contactService;

    @Autowired
    private ObjectMapper objectMapper;

    ContactDTO contactDTO;
    SimpleContactDTO simpleContactDTO;
    Contact contact;
    private final LocalDateTime testDateTime = LocalDateTime.of(2023, 1, 1, 12, 0);

    @BeforeEach
    void setUp(){
        contact = new Contact();
        contact.setContatoId(1L);
        contact.setContatoNome("Igor Viana");
        contact.setContatoEmail("igorviana@gmail.com");
        contact.setContatoCelular("11987654321");
        contact.setContatoTelefone("1133334444");
        contact.setContatoSnFavorito('n');
        contact.setContatoSnAtivo('s');
        contact.setContatoDhCad(testDateTime);

        simpleContactDTO = new SimpleContactDTO(
                "Igor Viana",
                "igorviana@gmail.com",
                "81982957722",
                "8282957722",
                'n',
                's'
        );

        contactDTO = new ContactDTO(
                1L,
                "Igor Viana",
                "igorviana@gmail.com",
                "81982957722",
                "8282957722",
                'n',
                's',
                testDateTime
        );
    }

    @Test
    void getAll() throws Exception {
        Mockito.when(contactService.getAllContacts()).thenReturn(List.of(contactDTO));

        contactController.perform(get("/api/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].contatoNome").value("Igor Viana"));
    }

    @Test
    void createContact() throws Exception {
        Mockito.when(contactService.createContact(any(SimpleContactDTO.class))).thenReturn(simpleContactDTO);

        contactController.perform(post("/api/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(simpleContactDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contatoEmail").value("igorviana@gmail.com"));
    }

    @Test
    void getContact() throws Exception {
        Mockito.when(contactService.getContactById(1L)).thenReturn(contactDTO);

        contactController.perform(get("/api/contacts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contatoId").value(1))
                .andExpect(jsonPath("$.contatoNome").value("Igor Viana"));

    }

    @Test
    void updateContact() throws Exception {
        Mockito.when(contactService.updateContact(eq(1L), any(SimpleContactDTO.class))).thenReturn(simpleContactDTO);

        contactController.perform(put("/api/contacts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(simpleContactDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contatoTelefone").value("8282957722"));
    }

    @Test
    void favoriteContact() throws Exception {
        Mockito.when(contactService.favoriteContact(1L)).thenReturn(simpleContactDTO);

        contactController.perform(patch("/api/contacts/1/favoritar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contatoSnFavorito").value("n"));
    }

    @Test
    void inactivateContact() throws Exception {
        SimpleContactDTO inactivatedContact = new SimpleContactDTO(
                "Igor Viana",
                "igorviana@gmail.com",
                "81982957722",
                "8282957722",
                'n',
                'n'
        );
        Mockito.when(contactService.inactivateContact(1L)).thenReturn(inactivatedContact);

        contactController.perform(patch("/api/contacts/1/inativar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contatoSnAtivo").value("n"));
    }
}