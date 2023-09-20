package br.com.kayCompany.Monalisa.domain.fatura;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record DadosDetalhamentoFatura(Long id, double valor, Status status, @JsonFormat(pattern = "dd/MM/yyyy")LocalDate vencimento , Long id_conta) {

    public DadosDetalhamentoFatura(Fatura fatura){
        this(fatura.getId(), fatura.getValor(), fatura.getStatus(), fatura.getVencimento() ,fatura.getCompra().getConta().getId());
    }
}
