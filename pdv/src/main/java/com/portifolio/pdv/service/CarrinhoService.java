package com.portifolio.pdv.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.portifolio.pdv.model.carrinho.Carrinho;
import com.portifolio.pdv.model.carrinho.ItemCarrinho;

@Service
public class CarrinhoService {
    private final Carrinho carrinho = new Carrinho();

    public List<ItemCarrinho> findAll(){
        List<ItemCarrinho> itens = new ArrayList<>();
        for(ItemCarrinho c : carrinho.getItens()){
            itens.add(c);
        }
        return itens;
    }

    public ItemCarrinho inserirItem(ItemCarrinho item){
        carrinho.adicionarItem(item);
        return item;
    }

    public void removerItem(Long id){
        carrinho.removerItem(id);
    }

    public void limparCarrinho(){
        carrinho.limpar();
    }
}
