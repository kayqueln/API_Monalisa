package br.com.kayCompany.Monalisa.dtos.compra;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record DadosInnerJoinCompra(
        Long id_compra,
        Long id_conta,
        String estabelecimento,
        Double valor,
        @JsonFormat(pattern = "dd/MM/yyyy")
        Date date,
        Long id_fatura
) {
}
