package com.projeto.pdv.model;

import java.util.List;

import com.projeto.pdv.dto.ProdutoDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double valor;

    @ManyToMany(mappedBy = "listaProdutos")
    private List<Pedido> pedidos;

    public Produto(ProdutoDTO p){
        this.id = p.getId();
        this.nome = p.getNome();
        this.valor = p.getValor();
    }
}
