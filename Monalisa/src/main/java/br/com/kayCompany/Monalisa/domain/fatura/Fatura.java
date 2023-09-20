package br.com.kayCompany.Monalisa.domain.fatura;

import br.com.kayCompany.Monalisa.domain.compra.Compra;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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
    private Status status;
    private LocalDate vencimento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compra_id")
    private Compra compra;

    public void pagarFatura() {
        this.status = Status.PAGA;
    }
}
