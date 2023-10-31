package br.com.kayCompany.Monalisa.dtos.compra;

import br.com.kayCompany.Monalisa.domain.Compra;

public record DadosDetalhamentoCompra(Long id, String estabelecimento, double valor, String data) {
    public DadosDetalhamentoCompra(Compra compra){
        this(compra.getId(), compra.getEstabelecimento(), compra.getValor(), compra.getData());
    }
}
