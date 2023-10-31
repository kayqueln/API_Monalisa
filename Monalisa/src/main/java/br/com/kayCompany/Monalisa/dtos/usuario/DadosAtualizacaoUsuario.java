package br.com.kayCompany.Monalisa.dtos.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUsuario(
        @NotNull
        String CPF,
        String nome,
        String sobrenome,
        String senha,
        String email
) {
}
