package br.com.kayCompany.Monalisa.repository;

import br.com.kayCompany.Monalisa.domain.Conta;
import br.com.kayCompany.Monalisa.dtos.conta.DadosInnerJoinConta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Query("""
            select new br.com.kayCompany.Monalisa.dtos.conta.DadosInnerJoinConta(c.id, c.abertura, c.limite, u.CPF, u.nome, u.sobrenome, u.email)
            from Conta c
            inner join c.usuario u
            where u.CPF = :CPF
            """)
    List<DadosInnerJoinConta> consultarConta(String CPF);

    @Query("""
            select c.id
            from Conta c
            inner join c.usuario u
            where u.CPF = :CPF
            """)
    Long consultarContaPeloCPF(String CPF);
}