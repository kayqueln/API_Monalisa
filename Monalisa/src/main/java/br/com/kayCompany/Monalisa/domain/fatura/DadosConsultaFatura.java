package br.com.kayCompany.Monalisa.domain.fatura;

import jakarta.validation.constraints.NotBlank;

public record DadosConsultaFatura(
        @NotBlank
        String CPF,
        Long id_fatura
) {
}
