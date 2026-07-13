package com.portifolio.pdv.model.carrinho;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Carrinho {
    private List<ItemCarrinho> itens = new ArrayList<>();

    public void adicionarItem(ItemCarrinho item){
        for(ItemCarrinho i : itens){
            if(item.getId().equals(i.getId())){
                i.setQuantidade(i.getQuantidade() + item.getQuantidade());
                return;
            }
        }
        itens.add(item);
    }

    public void removerItem(Long id){
        for(ItemCarrinho i : itens){
            if(i.getId().equals(id)){
                itens.remove(i);
                return;
            }
        }
    }

    public void limpar(){
        itens.clear();
    }
}
