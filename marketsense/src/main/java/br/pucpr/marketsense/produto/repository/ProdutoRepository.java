package br.pucpr.marketsense.produto.repository;

import br.pucpr.marketsense.produto.model.entity.*;
import org.springframework.data.jpa.repository.*;

public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

}
