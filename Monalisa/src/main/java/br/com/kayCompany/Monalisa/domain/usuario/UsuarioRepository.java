package br.com.kayCompany.Monalisa.domain.usuario;

import br.com.kayCompany.Monalisa.domain.conta.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    List<Usuario> findAllByAtivoTrue();

    Usuario findByCPF(String CPF);
}
