package br.com.kayCompany.Monalisa.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(name = "Usuario")
@Table(name = "Usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "CPF")
public class Usuario {
    private String nome;
    private String sobrenome;

    @Id
    private String CPF;
    private String senha;
    private String email;
    private Date nascimento;
    private boolean ativo;

    public String getNascimento(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(this.nascimento);
    }

    public Usuario(DadosCadastroUsuario dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.sobrenome = dados.sobrenome();
        this.CPF = dados.CPF();
        this.senha = dados.senha();
        this.email = dados.email();
        this.nascimento = dados.nascimento();
    }

    public void atualizarInformacoes(DadosAtualizacaoUsuario dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.sobrenome() != null){
            this.sobrenome = dados.sobrenome();
        }
        if(dados.senha() != null){
            this.senha = dados.senha();
        }
        if(dados.email() != null){
            this.email = dados.email();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
