package br.com.kayCompany.Monalisa.dtos.usuario;

import br.com.kayCompany.Monalisa.domain.Usuario;

public record DadosDetalhamentoUsuario(String nome, String sobrenome, String CPF, String Senha ,String email, String nascimento) {
    public DadosDetalhamentoUsuario(Usuario usuario){
        this(usuario.getNome(), usuario.getSobrenome(), usuario.getCPF(), usuario.getSenha(), usuario.getEmail(), usuario.getNascimento());
    }
}
