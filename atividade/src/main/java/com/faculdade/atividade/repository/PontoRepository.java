package com.faculdade.atividade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.faculdade.atividade.models.Ponto;

import jakarta.transaction.Transactional;

public interface PontoRepository extends JpaRepository<Ponto, Long>{

    @Query("SELECT p FROM Ponto p WHERE p.point > 0")
    List<Ponto> findAllWIthoutZeroPontos();

    @Modifying
    @Transactional
    @Query(value ="""
            INSERT INTO tb_ponto (point, sell_id, seller_id)
            SELECT 
                CASE 
                    WHEN rnk <= 10 THEN (110 - rnk * 10)
                    ELSE 0
                END AS point,
                v.id,
                v.seller_id
            FROM (
                SELECT 
                    v.id,
                    v.seller_id,
                    v.value,
                    ROW_NUMBER() OVER (ORDER BY v.value DESC) AS rnk
                FROM tb_venda v
                LEFT JOIN tb_ponto p ON p.sell_id = v.id
                WHERE p.id IS NULL AND v.in_person = 1
            ) AS v;
            """, nativeQuery = true)
    void makePoints();

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO tb_ponto (point, sell_id, seller_id)
            SELECT 0 AS point, v.id, v.seller_id
            FROM (
                    SELECT v.id, v.seller_id
                    FROM tb_venda v
                    LEFT JOIN tb_ponto p ON p.sell_id = v.id
                    WHERE p.id IS NULL AND v.in_person = 0
            ) AS v;
            """, nativeQuery = true)
    void makeZeroPoints();

    @Query("SELECT p FROM Ponto p WHERE p.seller.id = ?1 AND p.point > 0 ORDER BY p.point ASC")
    @Transactional
    List<Ponto> findAllOfOneSeller(Long id);
}
