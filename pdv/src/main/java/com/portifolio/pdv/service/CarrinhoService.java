package com.portifolio.pdv.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.portifolio.pdv.model.carrinho.Carrinho;
import com.portifolio.pdv.model.carrinho.ItemCarrinho;

@Service
public class CarrinhoService {
    private final Carrinho carrinho = new Carrinho();

    public List<ItemCarrinho> findAll(){
        return carrinho.getAll();
    }

    public void inserirItem(ItemCarrinho item){
        carrinho.inserirItem(item);
    }

    public void removerItem(Long id){
        carrinho.removerItem(id);
    }

    public void limparCarrinho(){
        carrinho.limparCarrinho();
    }

    public List<String> getTotais(){
        return carrinho.getTotais();
    }
}
