package br.com.kayCompany.Monalisa.controller;

import br.com.kayCompany.Monalisa.domain.conta.ContaRepository;
import br.com.kayCompany.Monalisa.domain.conta.DadosConsultaConta;
import br.com.kayCompany.Monalisa.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Tag(name = "Conta")
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaRepository repository;

    @GetMapping
    public ResponseEntity consultarConta(@RequestBody @Valid DadosConsultaConta dados){
        var conta = repository.consultarConta(dados.CPF());
        return ResponseEntity.ok(conta);
    }

}
