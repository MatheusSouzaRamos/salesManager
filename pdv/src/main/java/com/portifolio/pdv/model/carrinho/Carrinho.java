package com.portifolio.pdv.model.carrinho;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<ItemCarrinho> carrinho = new ArrayList<>();

    public List<ItemCarrinho> getAll(){
        return carrinho;
    }

    public void inserirItem(ItemCarrinho item){
        for(ItemCarrinho i : carrinho){
            if(i.getProduto().getId().equals(item.getProduto().getId())){
                i.setQuantidade(i.getQuantidade() + item.getQuantidade());
                return;
            }
        }
        carrinho.add(item);
    }

    public void removerItem(Long id){
        for(ItemCarrinho c : carrinho){
            if(c.getProduto().getId().equals(id)){
                carrinho.remove(c);
                return;
            }
        }
    }

    public void limparCarrinho(){
        carrinho.clear();
    }
}
