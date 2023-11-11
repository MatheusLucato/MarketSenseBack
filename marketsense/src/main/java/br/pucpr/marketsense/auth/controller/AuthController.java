package br.pucpr.marketsense.auth.controller;

import br.pucpr.marketsense.usuario.model.entity.Usuario;
import br.pucpr.marketsense.usuario.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UsuarioService usuarioService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<Usuario> login(@RequestParam String username, @RequestParam String senha) {
        Usuario user = usuarioService.encontrarUsuarioPorUsernameESenha(username, senha);

        if(user != null)
            return new ResponseEntity(user, HttpStatus.OK);
        else
            return new ResponseEntity(new Usuario(), HttpStatus.OK);

    }
}
