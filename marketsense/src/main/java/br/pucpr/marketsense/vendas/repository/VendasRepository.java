package br.pucpr.marketsense.vendas.repository;

import br.pucpr.marketsense.vendas.model.entity.Vendas;
import org.springframework.data.jpa.repository.*;


public interface VendasRepository extends JpaRepository<Vendas,Integer> {

}
