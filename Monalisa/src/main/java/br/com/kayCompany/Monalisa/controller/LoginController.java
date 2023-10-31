package br.com.kayCompany.Monalisa.controller;

import br.com.kayCompany.Monalisa.config.security.DadosTokenJWT;
import br.com.kayCompany.Monalisa.config.security.TokenService;
import br.com.kayCompany.Monalisa.dtos.autenticacao.DadosAutenticacao;
import br.com.kayCompany.Monalisa.domain.Usuario;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Login")
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager menager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.cpf(), dados.senha());
        var authentication = menager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
