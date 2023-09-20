package br.com.kayCompany.Monalisa.domain.conta;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroConta(
        @NotBlank
        String CPF
) {
}
