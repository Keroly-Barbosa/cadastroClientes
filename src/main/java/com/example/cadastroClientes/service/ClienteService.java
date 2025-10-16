package com.example.cadastroClientes.service;

import com.example.cadastroClientes.domain.Cliente;
import com.example.cadastroClientes.dto.ClienteDto;
import com.example.cadastroClientes.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente salvaCliente(ClienteDto clienteDto){

        Cliente clienteSalvo = new Cliente();
        clienteSalvo.setNome(clienteDto.getNome());
        clienteSalvo.setCpf(clienteDto.getCpf());
        clienteSalvo.setEndereco(clienteDto.getEndereco());

        clienteRepository.save(clienteSalvo);

        return clienteSalvo;
    };

    public Cliente buscaClientePorId(Long id){

      Optional<Cliente> clienteOptional =  clienteRepository.findById(id);

      if(clienteOptional.isEmpty()){
          throw new RuntimeException("cliente inexistente");
      }

      return clienteOptional.get();
    };

    public Cliente atualizaCliente(Long id, ClienteDto clienteDto){

        Optional<Cliente> clienteOptional =  clienteRepository.findById(id);

        if(clienteOptional.isEmpty()){
            throw new RuntimeException("cliente inexistente");
        }

        Cliente clienteAtualizado = new Cliente();
        clienteAtualizado.setId(clienteOptional.get().getId());
        clienteAtualizado.setNome(clienteDto.getNome());
        clienteAtualizado.setCpf(clienteDto.getCpf());
        clienteAtualizado.setEndereco(clienteDto.getEndereco());

        clienteRepository.updateNomeCpfEnderecoById(clienteAtualizado.getId(), clienteAtualizado.getNome(), clienteAtualizado.getCpf(), clienteAtualizado.getEndereco());

        return clienteAtualizado;
    };

    public void excluiClientePorId(Long id){

        Optional<Cliente> clienteOptional =  clienteRepository.findById(id);

        if(clienteOptional.isEmpty()){
            throw new RuntimeException("cliente inexistente");
        }

        clienteRepository.deleteById(clienteOptional.get().getId());
    };
}
