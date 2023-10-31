package br.com.kayCompany.Monalisa.dtos.fatura;

import jakarta.validation.constraints.NotNull;

public record DadosPagamentoFatura(
        @NotNull
        Long id_fatura,
        String CPF,
        String senha
) {
}
