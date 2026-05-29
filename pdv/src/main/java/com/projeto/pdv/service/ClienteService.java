package com.projeto.pdv.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.pdv.dto.ClienteDTO;
import com.projeto.pdv.model.Cliente;
import com.projeto.pdv.repository.ClienteRepository;
import com.projeto.pdv.service.exceptions.DatabaseIntegrityException;
import com.projeto.pdv.service.exceptions.EntityNotFoundException;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll(){
        List<Cliente> list = repository.findAll();
        List<ClienteDTO> dto = new ArrayList<>();
        for(Cliente c : list){
            dto.add(new ClienteDTO(c));
        }
        return dto;
    }

    @Transactional(readOnly = true)
    public ClienteDTO findByid(Long id){
        Optional<Cliente> obj = repository.findById(id);
        Cliente entity = obj.orElseThrow(() -> new EntityNotFoundException("Não Encontrado"));
        return new ClienteDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> findbyNome(String nome){
        List<Cliente> list = repository.findByNomeContainingIgnoreCase(nome);
        List<ClienteDTO> dto = new ArrayList<>();
        for(Cliente c : list){
            dto.add(new ClienteDTO(c));
        }
        return dto;
    }

    @Transactional
    public ClienteDTO insert(ClienteDTO dto){
        Cliente entity = new Cliente();
        // entity.setId(dto.getId());
        entity.setCpf(dto.getCpf());
        entity.setEndereco(dto.getEndereco());
        entity.setNome(dto.getNome());
        entity.setTelefone(dto.getTelefone());
        entity = repository.save(entity);
        return new ClienteDTO(entity);
    }

    @Transactional
    public ClienteDTO update(Long id, ClienteDTO dto){
        Cliente entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não encontrado."));
        // entity.setId(dto.getId());
        entity.setCpf(dto.getCpf());
        entity.setEndereco(dto.getEndereco());
        entity.setNome(dto.getNome());
        entity.setTelefone(dto.getTelefone());
        entity = repository.save(entity);
        return new ClienteDTO(entity);
    }

    @Transactional
    public void delete(Long id){
        try{
            repository.deleteById(id);
        }
        catch(Exception e){
            throw new DatabaseIntegrityException("Integridade violada.");
        }
    }
}
