package com.portifolio.pdv.model.carrinho;

import com.portifolio.pdv.model.Produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCarrinho {
    private Produto produto;
    private int quantidade;
}
