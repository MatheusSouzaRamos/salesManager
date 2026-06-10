package com.portifolio.pdv.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portifolio.pdv.dto.ClienteDTO;
import com.portifolio.pdv.model.Cliente;
import com.portifolio.pdv.repository.ClienteRepository;
import com.portifolio.pdv.service.exception.EntityNotFoundException;



@Service
public class ClienteService {
    private final ClienteRepository repository;

    ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll(){
        List<Cliente> clientes = repository.findAll(Sort.by("id"));
        List<ClienteDTO> dto = new ArrayList<>();
        for(Cliente c : clientes){
            dto.add(new ClienteDTO(c));
        }
        return dto;
    } 

    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id){
        Cliente entity = repository.findById(id).orElseThrow(()-> new EntityNotFoundException("Nao encontrado"));
        return new ClienteDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> findByNome(String nome){
        List<Cliente> clientes = repository.findByNomeContainingIgnoreCase(nome, Sort.by("id"));
        List<ClienteDTO> dto = new ArrayList<>();
        for(Cliente c : clientes){
            dto.add(new ClienteDTO(c));
        }
        return dto;
    }

    @Transactional
    public ClienteDTO insert(ClienteDTO dto){
        Cliente entity = new Cliente();

        entity.setNome(dto.getNome());
        entity.setCpf(dto.getCpf());
        entity.setTelefone(dto.getTelefone());
        // entity.setPedidos(dto.getPedidos());

        entity = repository.save(entity);
        return new ClienteDTO(entity);

    }

    public ClienteDTO update(Long id, ClienteDTO dto){
        Cliente entity = repository.findById(id).orElseThrow(()-> new EntityNotFoundException("Nao encontrado"));

        entity.setNome(dto.getNome());
        entity.setCpf(dto.getCpf());
        entity.setTelefone(dto.getTelefone());
        // entity.setPedidos(dto.getPedidos());
        
        entity = repository.save(entity);
        return new ClienteDTO(entity);

    }
}
