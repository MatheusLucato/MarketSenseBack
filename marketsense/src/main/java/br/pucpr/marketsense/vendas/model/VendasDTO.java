package br.pucpr.marketsense.vendas.model;

import br.pucpr.marketsense.produto.model.ProdutoDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Data
@Getter
@Setter
public class VendasDTO {

    private Integer id;
    private String dataVenda;
    private String idUsuario;
    private List<ProdutoDTO> produtos;
}
