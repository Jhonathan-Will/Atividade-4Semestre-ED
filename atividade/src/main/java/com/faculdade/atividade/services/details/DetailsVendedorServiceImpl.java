package com.faculdade.atividade.services.details;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.faculdade.atividade.data.DetailVendedorData;
import com.faculdade.atividade.repository.VendedorRepository;

@Component
public class DetailsVendedorServiceImpl implements UserDetailsService{

    private final VendedorRepository vendedorRepository;

    public DetailsVendedorServiceImpl(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return vendedorRepository.findByEmail(email).map(DetailVendedorData::new).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
