package br.pucpr.marketsense.vendas.service;

import br.pucpr.marketsense.vendas.model.entity.VendasProdutos;
import br.pucpr.marketsense.vendas.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class VendasProdutosService {

    @Autowired
    private VendasProdutosRepository vendasProdutosRepository;

    public VendasProdutos salvar(VendasProdutos vendasProdutos) {
        return vendasProdutosRepository.save(vendasProdutos);
    }

    public List<VendasProdutos> listar() {
        return vendasProdutosRepository.findAll();
    }

    public void excluir(Integer id) {
        vendasProdutosRepository.deleteById(id);
    }
    public VendasProdutos findById(Integer id) {
        return vendasProdutosRepository.findById(id).orElse(null);
    }

    public void excluirPorVendaId(Integer vendaId) {
        vendasProdutosRepository.excluirPorVendaId(vendaId);
    }

    public List<String> listarProdutosPorVenda(Integer vendaId) {
        return vendasProdutosRepository.findByVendaId(vendaId);
    }


}
