package com.portifolio.pdv.dto;

import com.portifolio.pdv.model.ItemPedido;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDTO {
    private Long id;
    private ProdutoDTO produto;
    private Long quantidade;
    private Double valorUnitario;

    public ItemPedidoDTO(ItemPedido item) {
        this.id = item.getId();
        this.produto = new ProdutoDTO(item.getProduto());
        this.quantidade = item.getQuantidade();
        this.valorUnitario = item.getValorUnitario();
    }
}
