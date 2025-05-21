package com.desafio.agendamento_telefonico.repository;

import com.desafio.agendamento_telefonico.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
