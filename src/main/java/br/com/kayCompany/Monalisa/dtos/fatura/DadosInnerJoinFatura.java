package br.com.kayCompany.Monalisa.dtos.fatura;

import br.com.kayCompany.Monalisa.uteis.StatusFatura;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record DadosInnerJoinFatura(
        Long id,
        double valor,
        StatusFatura statusFatura,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate vencimento
) {
}
