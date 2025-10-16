package com.example.cadastroClientes.repository;

import com.example.cadastroClientes.domain.Cliente;
import com.example.cadastroClientes.dto.ClienteDto;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class ClienteRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    ClienteRepository clienteRepository;

    @Test
    @DisplayName("Should get client successfully from DB")
    void findClientByIdCase1() {

        ClienteDto data = new ClienteDto("Keroly Barbosa", "99999999901" ,"Rua da Keroly 123");
        Cliente cliente = this.createCliente(data);

        Optional<Cliente> result = clienteRepository.findById(cliente.getId());

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get client successfully from DB when client not exists")
    void findClientByIdCase2() {

        Long id = 1L;

        Optional<Cliente> result = clienteRepository.findById(id);

        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Should update client successfully from DB")
    @Transactional
    void updateClientByIdCase3() {

        ClienteDto data = new ClienteDto("Keroly Barbosa", "99999999901" ,"Rua da Keroly 123");
        Cliente cliente = this.createCliente(data);


        clienteRepository.updateNomeCpfEnderecoById(cliente.getId(),
                "Keroly Silva", "99999999902", "Rua da Keroly 321");

        entityManager.clear();

        Cliente updated = clienteRepository.findById(cliente.getId()).orElseThrow();
        assertThat(updated.getNome()).isEqualTo("Keroly Silva");
        assertThat(updated.getCpf()).isEqualTo("99999999902");
        assertThat(updated.getEndereco()).isEqualTo("Rua da Keroly 321");
    }

    @Test
    @DisplayName("Should not update client successfully from DB when clint not exists")
    @Transactional
    void notUpdateClientByIdCase4() {

        // given: um ID inexistente (não há nenhum cliente no banco)
        Long idInexistente = 999L;

        // when / then: o método deve executar sem lançar exceção
        assertThatCode(() ->
                clienteRepository.updateNomeCpfEnderecoById(
                        idInexistente, "Novo Nome", "12345678900", "Rua Nova 123")
        ).doesNotThrowAnyException();
    }

    private Cliente createCliente(ClienteDto data){

        Cliente newCliente = new Cliente();
        newCliente.setNome(data.getNome());
        newCliente.setCpf(data.getCpf());
        newCliente.setEndereco(data.getEndereco());

        this.entityManager.persist(newCliente);
        return newCliente;
    };
}