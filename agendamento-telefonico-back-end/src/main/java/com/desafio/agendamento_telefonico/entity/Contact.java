package com.desafio.agendamento_telefonico.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "coontato", schema = "desafio")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contato_id")
    private Long contatoId;

    @Column(name = "contato_nome", length = 100, nullable = false)
    private String contatoNome;

    @Column(name = "contato_email", length = 255, nullable = false)
    private String contatoEmail;

    @Column(name = "contato_celular", length = 11, unique = true, nullable = false)
    private String contatoCelular;

    @Column(name = "contato_telefone", length = 10, nullable = false)
    private String contatoTelefone;

    @Column(name = "contato_sn_favorito", columnDefinition = "char(1)")
    private char contatoSnFavorito;

    @Column(name = "contato_sn_ativo", columnDefinition = "char(1)")
    private char contatoSnAtivo;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "contato_dh_cad", updatable = false)
    private LocalDateTime contatoDhCad;
}


