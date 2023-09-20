package br.com.kayCompany.Monalisa.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DadosDetalhamentoUsuario(String nome, String sobrenome, String CPF, String Senha ,String email, String nascimento) {
    public DadosDetalhamentoUsuario(Usuario usuario){
        this(usuario.getNome(), usuario.getSobrenome(), usuario.getCPF(), usuario.getSenha(), usuario.getEmail(), usuario.getNascimento());
    }
}
