package com.desafio.agendamento_telefonico.service;

import com.desafio.agendamento_telefonico.dto.contact.ContactDTO;
import com.desafio.agendamento_telefonico.dto.contact.SimpleContactDTO;
import com.desafio.agendamento_telefonico.entity.Contact;
import com.desafio.agendamento_telefonico.repository.ContactRepository;
import com.desafio.agendamento_telefonico.validation.ContactValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @InjectMocks
    private ContactService contactService;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private ContactValidation contactValidation;

    ContactDTO contactDTO;
    SimpleContactDTO simpleContactDTO;
    Contact contact;
    private final LocalDateTime testDateTime = LocalDateTime.of(2023, 1, 1, 12, 0);


    @BeforeEach
    void setUp() {
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
    void getAllContacts() {
        List<Contact> contacts = Collections.singletonList(contact);
        when(contactRepository.findAll()).thenReturn(contacts);

        List<ContactDTO> result = contactService.getAllContacts();

        assertEquals(1, result.size());
        assertEquals(contactDTO.contatoId(), result.getFirst().contatoId());
        verify(contactRepository, times(1)).findAll();
    }

    @Test
    void getContactById() {
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        SimpleContactDTO result = contactService.createContact(simpleContactDTO);

        assertNotNull(result);
        assertEquals(simpleContactDTO.contatoNome(), result.contatoNome());
        assertEquals(simpleContactDTO.contatoCelular(), result.contatoCelular());
        verify(contactRepository, times(1)).save(any(Contact.class));
    }

    @Test
    void favoriteContact() {
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));

        ContactDTO result = contactService.getContactById(1L);

        assertNotNull(result);
        assertEquals(contactDTO.contatoId(), result.contatoId());
        assertEquals(contactDTO.contatoNome(), result.contatoNome());
        verify(contactRepository, times(1)).findById(1L);
    }

    @Test
    void inactivateContact_VerificarTrocaDeAtivo() {
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        SimpleContactDTO result1 = contactService.inactivateContact(1L);
        assertEquals('n', contact.getContatoSnAtivo());

        SimpleContactDTO result2 = contactService.inactivateContact(1L);
        assertEquals('s', contact.getContatoSnAtivo());

        verify(contactRepository, times(2)).findById(1L);
        verify(contactRepository, times(2)).save(any(Contact.class));
    }

    @Test
    void updateContact() {

    }

    @Test
    void createContact() {
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);
        SimpleContactDTO result = contactService.createContact(simpleContactDTO);

        assertNotNull(result);
        assertEquals("Igor Viana", result.contatoNome());
        assertEquals("81982957722", result.contatoCelular());
        verify(contactRepository, times(1)).save(any(Contact.class));
        verify(contactValidation, times(1)).celularAlreadyExists(any(Contact.class), anyBoolean());
    }
}