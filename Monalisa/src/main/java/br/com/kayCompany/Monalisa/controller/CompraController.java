package br.com.kayCompany.Monalisa.controller;

import br.com.kayCompany.Monalisa.domain.Compra;
import br.com.kayCompany.Monalisa.dtos.compra.DadosDetalhamentoCompra;
import br.com.kayCompany.Monalisa.dtos.compra.DadosRealizarCompra;
import br.com.kayCompany.Monalisa.repository.ContaRepository;
import br.com.kayCompany.Monalisa.repository.FaturaRepository;
import br.com.kayCompany.Monalisa.repository.CompraRepository;
import br.com.kayCompany.Monalisa.uteis.Uteis;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;


@RestController
@Tag(name = "Compra")
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

    @GetMapping("/{cpf}")
    public ResponseEntity listarComprasPeloCPF(@PathVariable String cpf){
        var compras = repository.listarComprasPeloCPF(cpf);
        return ResponseEntity.ok(compras);
    }
}
