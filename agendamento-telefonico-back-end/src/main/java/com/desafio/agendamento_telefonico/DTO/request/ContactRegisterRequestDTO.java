package com.desafio.agendamento_telefonico.DTO.request;

public record ContactRegisterRequestDTO(
        String contatoNome,
        String contatoEmail,
        String contatoCelular,
        String contatoTelefone,
        char contatoSnFavorito,
        char contatoSnAtivo
) {
}
