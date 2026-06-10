package com.portifolio.pdv.dto;

import java.util.List;

import com.portifolio.pdv.model.Cliente;
import com.portifolio.pdv.model.Pedido;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ClienteDTO {
    private Long id;
    private String nome;
    private String telefone;
    private String cpf;
    private List<Pedido> pedidos;

    public ClienteDTO(){}

    public ClienteDTO(Long id, String nome, String telefone, String cpf, List<Pedido> pedidos){
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.pedidos = pedidos;
    }

    public ClienteDTO(Cliente cliente){
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.telefone = cliente.getTelefone();
        this.cpf = cliente.getCpf();
        this.pedidos = cliente.getPedidos();
    }
}
