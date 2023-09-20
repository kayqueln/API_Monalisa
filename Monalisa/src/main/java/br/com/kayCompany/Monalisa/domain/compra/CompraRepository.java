package br.com.kayCompany.Monalisa.domain.compra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Long> {

    @Query("""
            select new br.com.kayCompany.Monalisa.domain.compra.DadosInnerJoinCompra(cm.id, cn.id, cm.estabelecimento, cm.valor, cm.data, f.id)
            from Fatura f
            inner join f.compra cm
            inner join cm.conta cn
            inner join cn.usuario u
            where u.CPF = :CPF
            """)
    List<DadosInnerJoinCompra> listarComprasPeloCPF(String CPF);

    @Query("""  
            select c.id
            from Compra c
            inner join c.conta cn
            inner join cn.usuario u
            where u.CPF = :CPF
            order by c.id desc
            limit 1
            """)
    Long consultarCompraPeloCPF(String CPF);
}
