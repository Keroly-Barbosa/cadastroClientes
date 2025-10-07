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
    void atualizaNomeCpfEnderecoPorId (@Param("id") Long id, @Param("nome") String nome,  @Param("cpf") String cpf,  @Param("endereco") String endereco);

//    @Modifying
//    @Transactional
//    @Query("Update Cliente c Set c.cpf= :cpf where c.id = :id")
//    void atualizaCpfPorId (@Param("id") Long id, @Param("cpf") String cpf);
//
//    @Modifying
//    @Transactional
//    @Query("Update Cliente c Set c.endereco= :endereco where c.id = :id")
//    void atualizaEnderecoPorId (@Param("id") Long id, @Param("endereco") String endereco);


    //Optional<Cliente> findByCpf (String cpf);
}
