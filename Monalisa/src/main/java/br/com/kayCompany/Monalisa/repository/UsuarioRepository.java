package br.com.kayCompany.Monalisa.repository;

import br.com.kayCompany.Monalisa.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    List<Usuario> findAllByAtivoTrue();

    Usuario findByCPF(String CPF);
}
