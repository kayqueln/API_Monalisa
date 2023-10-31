package br.com.kayCompany.Monalisa.dtos.fatura;

import br.com.kayCompany.Monalisa.domain.Fatura;
import br.com.kayCompany.Monalisa.uteis.StatusFatura;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record DadosDetalhamentoFatura(Long id, double valor, StatusFatura statusFatura, @JsonFormat(pattern = "dd/MM/yyyy")LocalDate vencimento , Long id_conta) {

    public DadosDetalhamentoFatura(Fatura fatura){
        this(fatura.getId(), fatura.getValor(), fatura.getStatusFatura(), fatura.getVencimento() ,fatura.getCompra().getConta().getId());
    }
}
