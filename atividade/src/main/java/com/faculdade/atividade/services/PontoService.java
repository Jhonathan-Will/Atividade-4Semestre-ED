package com.faculdade.atividade.services;

import org.springframework.stereotype.Service;

import com.faculdade.atividade.repository.PontoRepository;

import jakarta.transaction.Transactional;

@Service
public class PontoService {

    private PontoRepository pontoRepository;

    public PontoService(PontoRepository pontoRepository) {
        this.pontoRepository = pontoRepository;
    }

    @Transactional
    public void savePoints(){
        
        pontoRepository.makePoints();
    }
}
