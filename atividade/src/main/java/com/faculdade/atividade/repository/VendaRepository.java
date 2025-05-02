package com.faculdade.atividade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.faculdade.atividade.models.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long>{

    List<Venda> findBySellerId(Long id);

    @Query("""
    SELECT v.seller.name
    FROM Venda v
    LEFT JOIN Ponto p ON p.sell = v
    WHERE p.id IS NULL
""")
    List<String> findSellerNameBySellsWithoutPoints();


    @Query("SELECT v.seller.name, v.value FROM Venda v LEFT JOIN Ponto p ON p.sell = v WHERE p.id is NULL")
    List<String> findSellsRangeByValueWithSellerName();
}
