package br.com.kayCompany.Monalisa.dtos.conta;

import jakarta.validation.constraints.NotBlank;

public record DadosConsultaConta(@NotBlank String CPF) {
}
