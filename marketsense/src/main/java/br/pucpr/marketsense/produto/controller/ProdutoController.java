package br.pucpr.marketsense.produto.controller;

import br.pucpr.marketsense.produto.model.*;
import br.pucpr.marketsense.produto.model.entity.*;
import br.pucpr.marketsense.produto.service.*;
import jakarta.validation.*;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    private ModelMapper modelMapper = new ModelMapper();;

    @PostMapping
    public ResponseEntity<ProdutoDTO> salvar(@Valid @RequestBody ProdutoDTO produtoDTO) {
        Produto produto = this.modelMapper.map(produtoDTO, Produto.class);
        produtoService.salvar(produto);
        produtoDTO.setId(produto.getId());
        return new ResponseEntity(produtoDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public List<ProdutoDTO> listar() {
        List<Produto> produtos = produtoService.listar();
        return produtos.stream().map(produto -> modelMapper.map(produto, ProdutoDTO.class)).
                collect(Collectors.toList());
    }

    @DeleteMapping("/{produtoId}")
    public ResponseEntity<ProdutoDTO> excluir(@PathVariable Integer produtoId) {
        Produto produtoDelete = this.modelMapper.map(produtoService.findById(produtoId), Produto.class);
        if (produtoDelete != null && produtoDelete.getId() != null) {
            produtoService.excluir(produtoDelete.getId());
        }
        return new ResponseEntity(produtoDelete, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ProdutoDTO> atualizar(@Valid @RequestBody ProdutoDTO produtoDTO) {
        Produto produtoParam = this.modelMapper.map(produtoDTO, Produto.class);
        Produto produto = produtoService.findById(produtoDTO.getId());
        produto.setNome(produtoParam.getNome());
        produto.setPreco(produtoParam.getPreco());
        produtoService.salvar(produto);
        return new ResponseEntity(produtoDTO, HttpStatus.OK);
    }


}
