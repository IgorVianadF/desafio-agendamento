package com.desafio.agendamento_telefonico.mapper;

import com.desafio.agendamento_telefonico.dto.contact.ContactDTO;
import com.desafio.agendamento_telefonico.dto.contact.SimpleContactDTO;
import com.desafio.agendamento_telefonico.entity.Contact;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactMapperTest {

    ContactDTO contactDTO;
    SimpleContactDTO simpleContactDTO;
    Contact contact;
    private final LocalDateTime testDateTime = LocalDateTime.of(2023, 1, 1, 12, 0);
    Long id = 1L;

    @BeforeEach
    void setUp() {
        contact = new Contact();
        contact.setContatoId(1L);
        contact.setContatoNome("Igor Viana");
        contact.setContatoEmail("igorviana@gmail.com");
        contact.setContatoCelular("81982957722");
        contact.setContatoTelefone("8282957722");
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

    @AfterEach
    void tearDown() {

    }

    @Test
    void toContact_DeveriaPassarSimpleDTOParaContato() {
        Contact contact = ContactMapper.toContact(id, simpleContactDTO);
        assertEquals(id, contact.getContatoId());
        assertEquals("Igor Viana", contact.getContatoNome());
        assertEquals("igorviana@gmail.com", contact.getContatoEmail());
        assertEquals("81982957722", contact.getContatoCelular());
        assertEquals("8282957722", contact.getContatoTelefone());
        assertEquals('n', contact.getContatoSnFavorito());
        assertEquals('s', contact.getContatoSnAtivo());
    }

    @Test
    void toContactWithoutId_DeveriaPassarSimpleDTOParaContatoSemId() {
        Contact contact = ContactMapper.toContactWithoutId(simpleContactDTO);
        assertEquals("Igor Viana", contact.getContatoNome());
        assertEquals("igorviana@gmail.com", contact.getContatoEmail());
        assertEquals("81982957722", contact.getContatoCelular());
        assertEquals("8282957722", contact.getContatoTelefone());
        assertEquals('n', contact.getContatoSnFavorito());
        assertEquals('s', contact.getContatoSnAtivo());
    }

    @Test
    void toSimpleContactDto_DeveriaPassarContatoParaSimpleDTO() {
        SimpleContactDTO simpleDto = ContactMapper.toSimpleContactDto(contact);
        assertEquals("Igor Viana", simpleDto.contatoNome());
        assertEquals("igorviana@gmail.com", simpleDto.contatoEmail());
        assertEquals("81982957722", simpleDto.contatoCelular());
        assertEquals("8282957722", simpleDto.contatoTelefone());
        assertEquals('n', simpleDto.contatoSnFavorito());
        assertEquals('s', simpleDto.contatoSnAtivo());
    }

    @Test
    void toContactDto() {
        ContactDTO completeDto = ContactMapper.toContactDto(contact);
        assertEquals(1L, completeDto.contatoId());
        assertEquals("Igor Viana", completeDto.contatoNome());
        assertEquals("igorviana@gmail.com", completeDto.contatoEmail());
        assertEquals("81982957722", completeDto.contatoCelular());
        assertEquals("8282957722", completeDto.contatoTelefone());
        assertEquals('n', completeDto.contatoSnFavorito());
        assertEquals('s', completeDto.contatoSnAtivo());
        assertEquals(testDateTime, completeDto.contatoDhCad());
    }
}