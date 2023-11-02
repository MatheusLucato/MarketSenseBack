package br.pucpr.marketsense.usuario.model;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Getter
@Setter
public class UsuarioDTO {

    private Integer id;
    @NotBlank
    private String login;
    private String nome;
    private String email;
}
