package br.pucpr.marketsense.vendas.repository;

import br.pucpr.marketsense.vendas.model.entity.VendasProdutos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VendasProdutosRepository extends JpaRepository<VendasProdutos,Integer> {


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Vendas_Produtos WHERE venda_id = :vendaId", nativeQuery = true)
    void excluirPorVendaId(Integer vendaId);


    @Query(value = "SELECT vp.produto_id FROM Vendas_Produtos vp WHERE vp.venda_id = :vendaId", nativeQuery = true)
    List<String> findByVendaId(Integer vendaId);
}
