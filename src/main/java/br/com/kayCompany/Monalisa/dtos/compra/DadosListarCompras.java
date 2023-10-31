package br.com.kayCompany.Monalisa.dtos.compra;

import jakarta.validation.constraints.NotBlank;

public record DadosListarCompras(
        @NotBlank
        String CPF
) {
}
