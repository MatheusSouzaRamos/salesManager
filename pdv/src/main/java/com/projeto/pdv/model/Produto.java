package com.projeto.pdv.model;

import jakarta.persistence.Entity;
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
    private Long id;
    private String nome;
    private Double valor;
}
