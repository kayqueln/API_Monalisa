package br.com.kayCompany.Monalisa.domain.compra;

import jakarta.validation.constraints.NotBlank;

public record DadosListarCompras(
        @NotBlank
        String CPF
) {
}
