package com.example.cadastroClientes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClienteDto {

    private String nome;
    private String cpf;
    private String endereco;
}
