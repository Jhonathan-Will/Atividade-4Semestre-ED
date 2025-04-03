package com.faculdade.atividade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faculdade.atividade.models.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Long>{
    Vendedor getById(Long id);
}
