package com.example.cadastroClientes.repository;

import com.example.cadastroClientes.domain.Cliente;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Override
    Optional<Cliente> findById(Long id);

    @Modifying
    @Transactional
    @Query("Update Cliente c Set c.nome= :nome, c.cpf= :cpf, c.endereco= :endereco  where c.id = :id")
    void updateNomeCpfEnderecoById (@Param("id") Long id, @Param("nome") String nome,  @Param("cpf") String cpf,  @Param("endereco") String endereco);

}
