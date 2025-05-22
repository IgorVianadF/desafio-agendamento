package com.desafio.agendamento_telefonico.dto.contact;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ContactDTO(
        Long contatoId,
        String contatoNome,
        String contatoEmail,
        String contatoCelular,
        String contatoTelefone,
        char contatoSnFavorito,
        char contatoSnAtivo,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime contatoDhCad
) {
}
