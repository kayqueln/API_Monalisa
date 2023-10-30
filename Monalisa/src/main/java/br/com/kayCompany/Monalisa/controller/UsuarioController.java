package br.com.kayCompany.Monalisa.controller;

import br.com.kayCompany.Monalisa.domain.conta.ContaRepository;
import br.com.kayCompany.Monalisa.domain.usuario.*;
import br.com.kayCompany.Monalisa.uteis.Uteis;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@Tag(name = "Usuario")
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private Uteis uteis;

    @GetMapping
    public ResponseEntity listar(){
        var lista = usuarioRepository.findAllByAtivoTrue();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriComponentsBuilder){
        var usuario = new Usuario(dados);
        if(!uteis.validaIdade(dados.nascimento())){
            usuarioRepository.save(usuario);
            uteis.abrirConta(dados.CPF());

            var uri = uriComponentsBuilder.path("/usuario{CPF}").buildAndExpand(usuario.getCPF()).toUri();
            return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
        }
        else {
            return ResponseEntity.badRequest().body("O usuário deve ser maior de idade para se cadastrar!");
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity alterar(@RequestBody @Valid DadosAtualizacaoUsuario dados){
        var usuario = usuarioRepository.getReferenceById(dados.CPF());
        usuario.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity excluir(@RequestBody DadosExclusaoUsuario dados){
        var usuario = usuarioRepository.getReferenceById(dados.CPF());
        usuario.excluir();
        return ResponseEntity.noContent().build();
    }
}
