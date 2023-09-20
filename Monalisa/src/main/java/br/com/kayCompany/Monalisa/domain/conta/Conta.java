package br.com.kayCompany.Monalisa.domain.conta;

import br.com.kayCompany.Monalisa.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "Conta")
@Table(name = "Contas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date abertura;
    private double limite;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_CPF")
    private Usuario usuario;
}
