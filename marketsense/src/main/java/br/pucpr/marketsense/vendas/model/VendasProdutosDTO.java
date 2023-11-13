package br.pucpr.marketsense.vendas.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class VendasProdutosDTO {

    private Integer id;
    private Integer idVenda;
    private Integer idProduto;

    public VendasProdutosDTO(Integer produtoId, Integer vendaId) {
        this.idProduto = produtoId;
        this.idVenda = vendaId;
    }
}
