package com.desafio.agendamento_telefonico.repository;

import com.desafio.agendamento_telefonico.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    boolean existsByContatoCelular(String celular);

    boolean existsByContatoCelularAndContatoIdIsNot(String celular, Long id);
}
