package com.projeto.pdv.dto;

import java.time.LocalDate;

import com.projeto.pdv.model.Produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
    private Long id;
    private String nome;
    private Double valor;

    public ProdutoDTO(Produto p){
        this.id = p.getId();
        this.nome = p.getNome();
        this.valor = p.getValor();
    }
}
