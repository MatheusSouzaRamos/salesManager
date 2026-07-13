package com.portifolio.pdv.model.carrinho;

import com.portifolio.pdv.model.Produto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemCarrinho {
    private Produto produto;
    private Long quantidade;

    public ItemCarrinho(Produto produto, Long quantidade){
        this.produto = produto;
        this.quantidade = quantidade;
    }
}
