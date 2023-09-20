package br.com.kayCompany.Monalisa.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosExclusaoUsuario(@NotBlank String CPF) {
}
