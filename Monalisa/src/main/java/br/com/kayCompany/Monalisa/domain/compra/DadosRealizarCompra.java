package br.com.kayCompany.Monalisa.domain.compra;

public record DadosRealizarCompra(
    String CPF,
    String senha,
    String estabelecimento,
    double valor
) {
}
