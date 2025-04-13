package com.faculdade.atividade.services;

import org.springframework.stereotype.Service;

import com.faculdade.atividade.dto.VendaDTO;
import com.faculdade.atividade.models.Venda;
import com.faculdade.atividade.repository.VendaRepository;

import jakarta.transaction.Transactional;

@Service
public class VendaService {

    private VendaRepository vendaRepository;

    public VendaService(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    @Transactional
    public Venda save(VendaDTO request) {

        Venda venda = new Venda();
        venda.setIn_person(request.in_person());
        venda.setValue(request.value());
        venda.setId_seller(request.id_seller());
        return vendaRepository.save(venda);
    }
}
