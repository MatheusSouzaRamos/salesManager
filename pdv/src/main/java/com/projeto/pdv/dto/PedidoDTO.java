package com.projeto.pdv.dto;

import java.util.List;

import com.projeto.pdv.model.Cliente;
import com.projeto.pdv.model.Produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PedidoDTO {
    private Long id;
    private Cliente cliente;
    private List<Produto> lista;
}
