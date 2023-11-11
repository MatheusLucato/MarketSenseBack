package br.pucpr.marketsense.produto.model;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Getter
@Setter
public class ProdutoDTO {

    private Integer id;
    private String preco;
    private String nome;
}
