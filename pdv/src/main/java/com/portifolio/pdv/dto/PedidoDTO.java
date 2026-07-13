package com.portifolio.pdv.dto;

import java.util.List;

import com.portifolio.pdv.model.Cliente;
import com.portifolio.pdv.model.Pedido;
import com.portifolio.pdv.model.Produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidoDTO {
    private Long id;
    private Cliente cliente;
    private List<Produto> produtos;

    public PedidoDTO(Pedido p){
        this.id = p.getId();
        this.cliente = p.getCliente();
        this.produtos = p.getProdutos();
    }
}
