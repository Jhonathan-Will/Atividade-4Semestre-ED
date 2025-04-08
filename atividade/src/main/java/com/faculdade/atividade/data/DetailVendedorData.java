package com.faculdade.atividade.data;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.faculdade.atividade.models.Vendedor;

public class DetailVendedorData implements UserDetails{

    private final Vendedor vendedor;

    public DetailVendedorData(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
       return vendedor.getPassword();
    }

    @Override
    public String getUsername() {
        return vendedor.getName();
    }

    public String getEmail() {
        return vendedor.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
