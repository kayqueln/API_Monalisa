package br.com.kayCompany.Monalisa.dtos.conta;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

public record DadosInnerJoinConta(
        Long id,
        @JsonFormat(pattern = "dd/MM/yyyy")
        Date abertura,
        Double limite,
        String CPF,
        String nome,
        String sobrenome,
        String email
) {
}
