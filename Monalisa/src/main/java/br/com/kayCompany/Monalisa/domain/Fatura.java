package br.com.kayCompany.Monalisa.domain;

import br.com.kayCompany.Monalisa.uteis.StatusFatura;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "Fatura")
@Table(name = "Faturas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Fatura {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double valor;

    @Enumerated(EnumType.STRING)
    private StatusFatura statusFatura;
    private LocalDate vencimento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compra_id")
    private Compra compra;

    public void pagarFatura() {
        this.statusFatura = StatusFatura.PAGA;
    }
}
