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
    private ClienteDTO cliente;
    private List<ItemPedidoDTO> itens;

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.cliente = new ClienteDTO(pedido.getCliente());

        this.itens = pedido.getItens().stream().map(ItemPedidoDTO::new).toList();
    }
}
