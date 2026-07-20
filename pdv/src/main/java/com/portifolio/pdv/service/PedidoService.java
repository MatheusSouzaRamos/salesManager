package com.portifolio.pdv.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.portifolio.pdv.dto.PedidoDTO;
import com.portifolio.pdv.dto.ProdutoDTO;
import com.portifolio.pdv.model.Cliente;
import com.portifolio.pdv.model.Pedido;
import com.portifolio.pdv.model.Produto;
import com.portifolio.pdv.repository.PedidoRepositoy;

@Service
public class PedidoService {
    private final PedidoRepositoy repository;

    PedidoService(PedidoRepositoy repository) {
        this.repository = repository;
    }

    public List<PedidoDTO> findAll(){
        List<Pedido> pedidos = repository.findAll();
        List<PedidoDTO> dto = new ArrayList<>();
        for(Pedido p : pedidos){
            dto.add(new PedidoDTO(p));
        }
        return dto;
    }

}
