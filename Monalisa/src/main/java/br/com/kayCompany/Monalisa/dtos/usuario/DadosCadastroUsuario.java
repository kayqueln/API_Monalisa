package br.com.kayCompany.Monalisa.dtos.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.util.Date;

public record DadosCadastroUsuario(
        @NotBlank
        String nome,
        @NotBlank
        String sobrenome,
        @Size(max = 11)
        String CPF,
        @Size(min = 6)
        String senha,
        @Email
        String email,
        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy")
        Date nascimento
) {
}
