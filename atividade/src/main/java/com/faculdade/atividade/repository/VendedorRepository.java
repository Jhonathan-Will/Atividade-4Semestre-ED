package com.faculdade.atividade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faculdade.atividade.models.Vendedor;
import java.util.Optional;


public interface VendedorRepository extends JpaRepository<Vendedor, Long>{

    Optional<Vendedor> findByEmail(String email);
}
