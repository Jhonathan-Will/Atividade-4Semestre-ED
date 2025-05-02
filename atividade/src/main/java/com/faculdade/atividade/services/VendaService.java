package com.faculdade.atividade.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.faculdade.atividade.dto.VendaDTO;
import com.faculdade.atividade.models.Venda;
import com.faculdade.atividade.models.Vendedor;
import com.faculdade.atividade.repository.VendaRepository;

import jakarta.transaction.Transactional;

@Service
public class VendaService {

    private VendaRepository vendaRepository;

    public VendaService(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    @Transactional
    public Venda save(VendaDTO request, Vendedor seller) {

        Venda venda = new Venda();
        venda.setIn_person(request.in_person());
        venda.setValue(request.value());
        venda.setSeller(seller);
        return vendaRepository.save(venda);
    }

    @Transactional
    public List<Venda> getAllSells() {
        return vendaRepository.findAll();
    }

    @Transactional 
    public List<Venda> getSellBySellerId(Long id) {
        return vendaRepository.findBySellerId(id);
    }

    @Transactional
    public List<String> getSellerNameBySellsWithoutPoints() {
        return vendaRepository.findSellerNameBySellsWithoutPoints();
    }

    @Transactional
    public List<String> getSellsRangeByValueWithSellerName() {
        List<String> sells = vendaRepository.findSellsRangeByValueWithSellerName();

        // Ordena a lista com base no valor numérico (em ordem decrescente)
        sells.sort((s1, s2) -> {
            Double value1 = Double.parseDouble(s1.split(",")[1].trim());
            Double value2 = Double.parseDouble(s2.split(",")[1].trim());
            return value2.compareTo(value1); // Ordem decrescente
        });

        return sells;
    }

    @Transactional
    public List<String> getSellsInPersonRangeByValueWithSellerName() {
        List<String> sells = vendaRepository.findSellsInPersonRangeByValueWithSellerName();

        // Ordena a lista com base no valor numérico (em ordem decrescente)
        sells.sort((s1, s2) -> {
            Double value1 = Double.parseDouble(s1.split(",")[1].trim());
            Double value2 = Double.parseDouble(s2.split(",")[1].trim());
            return value2.compareTo(value1); // Ordem decrescente
        });

        return sells;
    }
}
