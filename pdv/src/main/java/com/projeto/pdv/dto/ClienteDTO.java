package com.projeto.pdv.dto;

import com.projeto.pdv.model.Cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private Long id;
    private String cpf;
    private String nome;
    private String endereco;
    private String telefone;

    public ClienteDTO(Cliente c){
        this.id = c.getId();
        this.cpf = c.getCpf();
        this.nome = c.getNome();
        this.endereco = c.getEndereco();
        this.telefone = c.getTelefone();
    }
}
