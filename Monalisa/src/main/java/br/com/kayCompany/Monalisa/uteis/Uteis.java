package br.com.kayCompany.Monalisa.uteis;

import br.com.kayCompany.Monalisa.domain.compra.CompraRepository;
import br.com.kayCompany.Monalisa.domain.compra.DadosRealizarCompra;
import br.com.kayCompany.Monalisa.domain.conta.Conta;
import br.com.kayCompany.Monalisa.domain.conta.ContaRepository;
import br.com.kayCompany.Monalisa.domain.fatura.*;
import br.com.kayCompany.Monalisa.domain.usuario.Usuario;
import br.com.kayCompany.Monalisa.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class Uteis {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private FaturaRepository faturaRepository;

    @Autowired
    private ContaRepository contaRepository;

    public boolean validaIdade(Date idade){
        var idadeUsuario = idade;
        var dataConvertida = idadeUsuario.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        var dataAtual = LocalDate.now();

        var diferenca = ChronoUnit.YEARS.between(dataConvertida, dataAtual);

        return diferenca < 18;
    }

    public void abrirConta(String CPF){
        var usuario = usuarioRepository.getReferenceById(CPF);
        Conta conta = new Conta(null, new Date(), 1000, usuario);
        contaRepository.save(conta);
    }


    public boolean validarSenha(String CPF, String senha) {
        Usuario usuario = usuarioRepository.getReferenceById(CPF);

        return senha.equals(usuario.getSenha());
    }

    public void abrirFatura(DadosRealizarCompra dados){
        var idFatura = faturaRepository.ConsultarPeloCPF(dados.CPF());

        if(idFatura == null){
            var idCompra = compraRepository.consultarCompraPeloCPF(dados.CPF());
            var compra = compraRepository.getReferenceById(idCompra);
            Fatura fatura = new Fatura(null, dados.valor(), Status.ABERTA, LocalDate.now().plusMonths(1) ,compra);
            faturaRepository.save(fatura);
        } else {
            var consultaFatura = faturaRepository.getReferenceById(idFatura);

            if(consultaFatura.getStatus() == Status.PAGA) {
                var idCompra = compraRepository.consultarCompraPeloCPF(dados.CPF());
                var compra = compraRepository.getReferenceById(idCompra);
                Fatura fatura = new Fatura(null, dados.valor(), Status.ABERTA, LocalDate.now().plusMonths(1) ,compra);
                faturaRepository.save(fatura);
            } else {
                var fatura = faturaRepository.getReferenceById(idFatura);
                var valorFatura = fatura.getValor() + dados.valor();
                fatura.setValor(valorFatura);
            }
        }
    }

    public void ajustarLimite(DadosRealizarCompra dados){
        var idFatura = faturaRepository.ConsultarPeloCPF(dados.CPF());
        var fatura = faturaRepository.getReferenceById(idFatura);

        var idConta = contaRepository.consultarContaPeloCPF(dados.CPF());
        var conta = contaRepository.getReferenceById(idConta);

        if (fatura.getValor() > conta.getLimite()){
            conta.setLimite(fatura.getValor());
        }
    }

    public void checarStatus(DadosConsultaFatura dados){
        var faturas = faturaRepository.listarFaturasPeloCPF(dados.CPF());

        for (DadosInnerJoinFatura fatura : faturas ) {
            var consultaDetalhada = faturaRepository.getReferenceById(fatura.id());

            if (consultaDetalhada.getStatus() != Status.PAGA) {
                if (LocalDate.now().isAfter(consultaDetalhada.getVencimento())) {
                    consultaDetalhada.setStatus(Status.ATRASADA);
                    faturaRepository.save(consultaDetalhada);
                } else {
                    consultaDetalhada.setStatus(Status.ABERTA);
                    faturaRepository.save(consultaDetalhada);
                }
            }
        }
    }
}
