package br.com.kayCompany.Monalisa.dtos.conta;

import br.com.kayCompany.Monalisa.domain.Conta;

import java.util.Date;

public record DadosDetalhamentoConta(Long id, Date abertura) {
    public DadosDetalhamentoConta(Conta conta){
        this(conta.getId(), conta.getAbertura());
    }
}
