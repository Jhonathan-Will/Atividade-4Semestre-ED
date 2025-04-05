package com.faculdade.atividade.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.faculdade.atividade.models.Vendedor;

public class DetailVendedorData implements UserDetails{

    private final Optional<Vendedor> vendedor;

    public DetailVendedorData(Optional<Vendedor> vendedor) {
        this.vendedor = vendedor;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
       return vendedor.orElse(new Vendedor()).getPassword();
    }

    @Override
    public String getUsername() {
        return vendedor.orElse(new Vendedor()).getName();
    }

    public String getEmail() {
        return vendedor.orElse(new Vendedor()).getEmail();
    }

}
