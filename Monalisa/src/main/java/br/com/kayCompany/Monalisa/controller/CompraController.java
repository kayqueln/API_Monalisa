package br.com.kayCompany.Monalisa.controller;

import br.com.kayCompany.Monalisa.domain.compra.*;
import br.com.kayCompany.Monalisa.domain.conta.ContaRepository;
import br.com.kayCompany.Monalisa.domain.fatura.FaturaRepository;
import br.com.kayCompany.Monalisa.uteis.Uteis;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;


@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private Uteis uteis;

    @Autowired
    private CompraRepository repository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private FaturaRepository faturaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity comprar(@RequestBody @Valid DadosRealizarCompra dados, UriComponentsBuilder uriComponentsBuilder){
        var idConta = contaRepository.consultarContaPeloCPF(dados.CPF());
        var conta = contaRepository.getReferenceById(idConta);
        Compra compra = new Compra(null, dados.estabelecimento(), dados.valor(), new Date(), conta);

        if(uteis.validarSenha(dados.CPF(), dados.senha())){
            repository.save(compra);
            uteis.abrirFatura(dados);
            uteis.ajustarLimite(dados);
        } else {
            return ResponseEntity.badRequest().body("Não autorizada! Verifique se o usuário exixte e se a senha está correta.");
        }

        var uri = uriComponentsBuilder.path("/compra{id}").buildAndExpand(compra.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoCompra(compra));
    }

    @GetMapping
    public ResponseEntity listarComprasPeloCPF(@RequestBody @Valid DadosListarCompras dados){
        var compras = repository.listarComprasPeloCPF(dados.CPF());
        return ResponseEntity.ok(compras);
    }
}
