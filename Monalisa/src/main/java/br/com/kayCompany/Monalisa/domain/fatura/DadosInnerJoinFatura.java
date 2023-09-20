package br.com.kayCompany.Monalisa.domain.fatura;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record DadosInnerJoinFatura(
        Long id,
        double valor,
        Status status,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate vencimento
) {
}
