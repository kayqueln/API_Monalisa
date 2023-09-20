package br.com.kayCompany.Monalisa.domain.fatura;

import jakarta.validation.constraints.NotNull;

public record DadosPagamentoFatura(
        @NotNull
        Long id_fatura,
        String CPF,
        String senha
) {
}
