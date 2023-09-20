package br.com.kayCompany.Monalisa.domain.conta;

import br.com.kayCompany.Monalisa.domain.usuario.Usuario;

import java.util.Date;

public record DadosDetalhamentoConta(Long id, Date abertura) {
    public DadosDetalhamentoConta(Conta conta){
        this(conta.getId(), conta.getAbertura());
    }
}
