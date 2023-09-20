package br.com.kayCompany.Monalisa.domain.usuario;

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
