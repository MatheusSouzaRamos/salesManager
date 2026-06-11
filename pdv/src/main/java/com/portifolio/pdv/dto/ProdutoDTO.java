package com.portifolio.pdv.dto;

import java.util.List;

import com.portifolio.pdv.model.Pedido;
import com.portifolio.pdv.model.Produto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {
    private Long id;

    private String nome;

    private Double valor;


    private List<Pedido> pedidos;

    public ProdutoDTO(){};

    public ProdutoDTO(Produto produto){
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.pedidos = produto.getPedidos();
    }
}
