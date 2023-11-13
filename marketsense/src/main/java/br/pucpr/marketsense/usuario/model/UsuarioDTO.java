package br.pucpr.marketsense.usuario.model;

import lombok.*;

@Data
@Getter
@Setter
public class UsuarioDTO {

    private Integer id;
    private String senha;
    private String nome;
    private Boolean admin;
}
