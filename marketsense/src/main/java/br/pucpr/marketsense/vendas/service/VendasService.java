package br.pucpr.marketsense.vendas.service;

import br.pucpr.marketsense.vendas.model.entity.Vendas;
import br.pucpr.marketsense.vendas.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class VendasService {

    @Autowired
    private VendasRepository vendasRepository;

    public Vendas salvar(Vendas vendas) {
        return vendasRepository.save(vendas);
    }

    public List<Vendas> listar() {
        return vendasRepository.findAll();
    }

    public void excluir(Integer id) {
        vendasRepository.deleteById(id);
    }
    public Vendas findById(Integer id) {
        return vendasRepository.findById(id).orElse(null);
    }


}
