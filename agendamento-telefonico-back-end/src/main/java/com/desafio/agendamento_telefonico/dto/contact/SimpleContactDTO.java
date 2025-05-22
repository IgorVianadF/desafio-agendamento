package com.desafio.agendamento_telefonico.dto.contact;

public record SimpleContactDTO(
        String contatoNome,
        String contatoEmail,
        String contatoCelular,
        String contatoTelefone,
        char contatoSnFavorito,
        char contatoSnAtivo
) {
}
