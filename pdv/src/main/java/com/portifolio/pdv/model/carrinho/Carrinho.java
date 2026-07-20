package com.portifolio.pdv.model.carrinho;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Carrinho {
    private List<ItemCarrinho> carrinho = new ArrayList<>();

    public List<ItemCarrinho> getAll(){
        return carrinho;
    }

    public void inserirItem(ItemCarrinho item){
        for(Iterator<ItemCarrinho> it = carrinho.iterator(); it.hasNext();){
            ItemCarrinho c = it.next();

            if(c.getProduto().getId().equals(item.getProduto().getId())){
                long qtd = c.getQuantidade() + item.getQuantidade();

                if(qtd <= 0 ){
                    it.remove();;
                }
                else {
                    c.setQuantidade(qtd);
                }
                return;
            }
        }

        if(item.getQuantidade() > 0){
            carrinho.add(item);
        }
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

    public List<String> getTotais(){
        if(carrinho.isEmpty()){
            List<String> lista = new ArrayList<>(List.of("0","0","0"));
            return lista;
        }

        double valorTotal = 0;
        int totalItens = 0;
        
        for (ItemCarrinho c : carrinho){
            valorTotal = valorTotal + (c.getProduto().getValor() *c.getQuantidade());
            totalItens += c.getQuantidade();
        }
        
        int distintos = carrinho.size();

        List<String> lista = new ArrayList<>(List.of(String.valueOf(valorTotal), String.valueOf(totalItens), String.valueOf(distintos)));

        return lista;
    }

 
}