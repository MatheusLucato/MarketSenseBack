package br.pucpr.marketsense.vendas.model.entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@Getter
@Setter
@Entity
@Table(name = "VENDAS")
public class Vendas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "DATA_VENDA")
    private String dataVenda;

    @Column(name = "usuario_id")
    private String idUsuario;

}
