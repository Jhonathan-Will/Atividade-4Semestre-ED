package com.faculdade.atividade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faculdade.atividade.models.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long>{

    List<Venda> findBySellerId(Long id);

}
