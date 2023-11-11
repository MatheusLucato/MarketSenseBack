package br.pucpr.marketsense.usuario.service;

import br.pucpr.marketsense.usuario.model.entity.*;
import br.pucpr.marketsense.usuario.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public void excluir(Integer id) {
        usuarioRepository.deleteById(id);
    }
    public Usuario findById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }
    public Usuario encontrarUsuarioPorUsernameESenha(String username, String senha) {
        return usuarioRepository.findByNomeAndSenha(username, senha).orElse(null);
    }


}
