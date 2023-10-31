package br.com.kayCompany.Monalisa.repository;

import br.com.kayCompany.Monalisa.dtos.fatura.DadosInnerJoinFatura;
import br.com.kayCompany.Monalisa.domain.Fatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FaturaRepository extends JpaRepository<Fatura, Long> {

    @Query("""
            select new br.com.kayCompany.Monalisa.dtos.fatura.DadosInnerJoinFatura(f.id, f.valor, f.statusFatura, f.vencimento)
            from Fatura f
            inner join f.compra c
            inner join c.conta cn
            inner join cn.usuario u
            where u.CPF = :CPF
            """)
    List<DadosInnerJoinFatura> listarFaturasPeloCPF(String CPF);


    //br.com.kayCompany.Monalisa.dtos.fatura.DadosInnerJoinFatura

    @Query("""
            select f.id
            from Fatura f
            inner join f.compra c
            inner join c.conta cn
            inner join cn.usuario u
            where u.CPF = :CPF
            order by f.id desc
            limit 1
            """)
    Long ConsultarPeloCPF(String CPF);
}
