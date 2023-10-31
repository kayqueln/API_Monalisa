package br.com.kayCompany.Monalisa.dtos.compra;

public record DadosRealizarCompra(
    String CPF,
    String senha,
    String estabelecimento,
    double valor
) {
}
