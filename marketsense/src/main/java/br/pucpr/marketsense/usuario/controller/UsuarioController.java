package br.pucpr.marketsense.usuario.controller;

import br.pucpr.marketsense.usuario.model.*;
import br.pucpr.marketsense.usuario.model.entity.*;
import br.pucpr.marketsense.usuario.service.*;
import jakarta.validation.*;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    private ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = this.modelMapper.map(usuarioDTO, Usuario.class);
        usuarioService.salvar(usuario);
        usuarioDTO.setId(usuario.getId());
        return new ResponseEntity(usuarioDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public List<UsuarioDTO> listar() {
        List<Usuario> usuarios = usuarioService.listar();
        return usuarios.stream().map(usuario -> modelMapper.map(usuario, UsuarioDTO.class)).
                collect(Collectors.toList());
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<UsuarioDTO> excluir(@PathVariable Integer usuarioId) {
        Usuario userDelete = this.modelMapper.map(usuarioService.findById(usuarioId), Usuario.class);
        if (userDelete != null && userDelete.getId() != null) {
            usuarioService.excluir(userDelete.getId());
        }
        return new ResponseEntity(userDelete, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> atualizar(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuarioParam = this.modelMapper.map(usuarioDTO, Usuario.class);
        Usuario usuario = usuarioService.findById(usuarioDTO.getId());
        usuario.setNome(usuarioParam.getNome());
        usuario.setSenha(usuarioParam.getSenha());
        usuario.setAdmin(usuarioParam.getAdmin());
        usuarioService.salvar(usuario);
        return new ResponseEntity(usuarioDTO, HttpStatus.OK);
    }


}
