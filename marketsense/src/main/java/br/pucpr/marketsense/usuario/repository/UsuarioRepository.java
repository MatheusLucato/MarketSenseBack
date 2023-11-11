package br.pucpr.marketsense.usuario.repository;

import br.pucpr.marketsense.usuario.model.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    @Query("SELECT u FROM Usuario u WHERE u.nome = :username AND u.senha = :senha")
    Optional<Usuario> findByNomeAndSenha(@Param("username") String username, @Param("senha") String senha);

}
