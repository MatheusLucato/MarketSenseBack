package br.pucpr.marketsense.produto.service;

import br.pucpr.marketsense.produto.model.entity.Produto;
import br.pucpr.marketsense.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void excluir(Integer id) {
        produtoRepository.deleteById(id);
    }

    public Produto findById(Integer id) {
        return produtoRepository.findById(id).orElse(null);
    }




}
