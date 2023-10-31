package br.com.kayCompany.Monalisa.controller;


import br.com.kayCompany.Monalisa.dtos.fatura.DadosConsultaFatura;
import br.com.kayCompany.Monalisa.dtos.fatura.DadosDetalhamentoFatura;
import br.com.kayCompany.Monalisa.dtos.fatura.DadosPagamentoFatura;
import br.com.kayCompany.Monalisa.repository.FaturaRepository;
import br.com.kayCompany.Monalisa.uteis.StatusFatura;
import br.com.kayCompany.Monalisa.uteis.Uteis;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Fatura")
@RequestMapping("/fatura")
public class FaturaController {

    @Autowired
    private FaturaRepository repository;

    @Autowired
    private Uteis uteis;

    @GetMapping
    public ResponseEntity consultarFaturas(@RequestBody @Valid DadosConsultaFatura dados){
        uteis.checarStatus(dados);
        var faturas = repository.listarFaturasPeloCPF(dados.CPF());

        return ResponseEntity.ok(faturas);
    }

    @GetMapping("/search")
    public ResponseEntity consultar(@RequestBody @Valid DadosConsultaFatura dados) {
        uteis.checarStatus(dados);
        var fatura = repository.getReferenceById(dados.id_fatura());

        return ResponseEntity.ok(new DadosDetalhamentoFatura(fatura));
    }


    @PutMapping
    @Transactional
    public ResponseEntity pagarFatura(@RequestBody @Valid DadosPagamentoFatura dados){
        var fatura = repository.getReferenceById(dados.id_fatura());

        if (fatura.getStatusFatura() != StatusFatura.PAGA) {
            if (uteis.validarSenha(dados.CPF(), dados.senha())) {
                fatura.pagarFatura();
                return ResponseEntity.ok(new DadosDetalhamentoFatura(fatura));
            } else {
                return ResponseEntity.badRequest().body("Não autorizada! Verifique se o usuário exixte e se a senha está correta.");
            }
        } else {
            return ResponseEntity.ok("Essa fatura já foi paga :)");
        }
    }
}
