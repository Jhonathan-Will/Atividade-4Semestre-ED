package com.faculdade.atividade.services.details;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.faculdade.atividade.data.DetailVendedorData;
import com.faculdade.atividade.models.Vendedor;
import com.faculdade.atividade.repository.VendedorRepository;

@Component
public class DetailsVendedorServiceImpl implements UserDetailsService{

    private final VendedorRepository vendedorRepository;

    public DetailsVendedorServiceImpl(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Vendedor> vendedor = vendedorRepository.findByEmail(email);
        
        if(vendedor.isEmpty()){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return new DetailVendedorData(vendedor);
    }

}
