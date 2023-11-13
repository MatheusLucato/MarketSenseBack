package br.pucpr.marketsense.vendas.controller;

import br.pucpr.marketsense.produto.model.ProdutoDTO;
import br.pucpr.marketsense.produto.model.entity.Produto;
import br.pucpr.marketsense.produto.service.ProdutoService;
import br.pucpr.marketsense.vendas.model.VendasDTO;
import br.pucpr.marketsense.vendas.model.VendasProdutosDTO;
import br.pucpr.marketsense.vendas.model.entity.Vendas;
import br.pucpr.marketsense.vendas.model.entity.VendasProdutos;
import br.pucpr.marketsense.vendas.service.VendasProdutosService;
import br.pucpr.marketsense.vendas.service.VendasService;
import jakarta.validation.*;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.*;

@RestController
@RequestMapping("/vendas")
public class VendasController {

    @Autowired
    private VendasService vendasService;
    @Autowired
    private VendasProdutosService vendasProdutosService;
    @Autowired
    private ProdutoService produtoService;
    private ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<String> criarVenda(@Valid @RequestBody VendasDTO vendasDTO) {
        vendasDTO.setDataVenda(LocalDate.parse(vendasDTO.getDataVenda(), DateTimeFormatter.ofPattern("MM/dd/yyyy"))
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        Vendas venda = this.modelMapper.map(vendasDTO, Vendas.class);
        vendasService.salvar(venda);

        vendasDTO.getProdutos().stream()
                .map(produtoDTO -> produtoService.findById(produtoDTO.getId()).getId())
                .collect(Collectors.toList())
                .forEach(produtoId ->
                vendasProdutosService.salvar(
                        this.modelMapper.map(new VendasProdutosDTO(produtoId, venda.getId()), VendasProdutos.class)));

        return ResponseEntity.status(HttpStatus.CREATED).body("Venda criada com sucesso");
    }

    @GetMapping
    public List<VendasDTO> listar() {
        List<Vendas> vendas = vendasService.listar();
        return vendas.stream().map(venda -> modelMapper.map(venda, VendasDTO.class)).
                collect(Collectors.toList());
    }

    @GetMapping("/{vendaId}")
    public List<ProdutoDTO> listarProdutosporVenda(@PathVariable Integer vendaId) {
        List<String> produtosId = vendasProdutosService.listarProdutosPorVenda(vendaId);

        return produtosId.stream()
                .map(produtoId -> {
                    Produto produto = produtoService.findById(Integer.parseInt(produtoId));
                    return modelMapper.map(produto, ProdutoDTO.class);
                })
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{vendaId}")
    public ResponseEntity<VendasDTO> excluir(@PathVariable Integer vendaId) {
        Vendas vendaDelete = this.modelMapper.map(vendasService.findById(vendaId), Vendas.class);

        if (vendaDelete != null && vendaDelete.getId() != null) {
            vendasProdutosService.excluirPorVendaId(vendaDelete.getId());
            vendasService.excluir(vendaDelete.getId());

        }
        return new ResponseEntity(vendaDelete, HttpStatus.OK);
    }
}
