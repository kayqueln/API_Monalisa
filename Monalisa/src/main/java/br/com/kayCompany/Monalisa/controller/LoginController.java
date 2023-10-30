package br.com.kayCompany.Monalisa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController {

    @PostMapping
    public ResponseEntity logar(String cpf, String senha){


        return null;
    }
}
