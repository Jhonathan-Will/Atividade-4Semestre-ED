package com.faculdade.atividade.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.faculdade.atividade.dto.VendedorDto;
import com.faculdade.atividade.models.Vendedor;
import com.faculdade.atividade.repository.VendedorRepository;

import jakarta.transaction.Transactional;

@Service
public class VendedorService {

    private VendedorRepository vendedorRepository;

    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    @Transactional
    public Vendedor saveSeller(VendedorDto vendedordto){
        Vendedor seller = new Vendedor();
        seller.setName(vendedordto.name());
        seller.setEmail(vendedordto.email());
        seller.setPassword(vendedordto.password());

        return vendedorRepository.save(seller);
    }

    @Transactional
    public Optional<Vendedor> getSeller(Long id){
        return vendedorRepository.findById(id);
    }

    @Transactional
    public List<Vendedor> getAll(){
        return vendedorRepository.findAll();
    }

}
