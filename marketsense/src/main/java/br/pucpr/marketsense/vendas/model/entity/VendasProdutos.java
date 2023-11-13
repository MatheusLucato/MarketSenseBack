package br.pucpr.marketsense.vendas.model.entity;

import br.pucpr.marketsense.produto.model.entity.Produto;
import jakarta.persistence.*;
import lombok.*;
@Data
@Getter
@Setter
@Entity
@Table(name = "VENDAS_PRODUTOS")
public class VendasProdutos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Vendas venda;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;


}

