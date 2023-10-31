package br.com.kayCompany.Monalisa.dtos.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosExclusaoUsuario(@NotBlank String CPF) {
}
