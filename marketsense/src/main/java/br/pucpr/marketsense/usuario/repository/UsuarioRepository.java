package br.pucpr.marketsense.usuario.repository;

import br.pucpr.marketsense.usuario.model.entity.*;
import org.springframework.data.jpa.repository.*;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
}
