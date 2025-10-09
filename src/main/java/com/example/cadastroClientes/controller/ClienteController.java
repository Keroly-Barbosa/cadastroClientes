package com.example.cadastroClientes.controller;

import com.example.cadastroClientes.domain.Cliente;
import com.example.cadastroClientes.dto.ClienteDto;
import com.example.cadastroClientes.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

@PostMapping
    public ResponseEntity<Cliente> criaCliente (@RequestBody ClienteDto clienteDto){

        Cliente cliente = clienteService.salvaCliente(clienteDto);

        return ResponseEntity.ok(cliente);
    };

@GetMapping("/public/{id}")
    public ResponseEntity<Cliente> buscaClienteId(@PathVariable Long id){

       Cliente clienteEncontrado = clienteService.buscaClientePorId(id);

       return ResponseEntity.ok(clienteEncontrado);
    };

@PutMapping("{id}")
    public ResponseEntity<Cliente> atualizaClienteId(@PathVariable Long id, @RequestBody ClienteDto clienteDto){

        Cliente clienteAtualizado  = clienteService.atualizaCliente(id,clienteDto);

        return ResponseEntity.ok(clienteAtualizado);

    };

@DeleteMapping("{id}")
    public ResponseEntity<?> deleteClienteId(@PathVariable Long id){

        clienteService.excluiClientePorId(id);

        return ResponseEntity.noContent().build();
    };

}
