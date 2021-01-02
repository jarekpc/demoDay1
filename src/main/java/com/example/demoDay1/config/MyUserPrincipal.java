package com.example.demoDay1.config;

import com.example.demoDay1.app.Person;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
public class MyUserPrincipal implements UserDetails {

    private Person person;
    private User user;

    public MyUserPrincipal(Person person) {
        this.person = person;
        this.user = new User(person.getUsername(), person.getPassword(), getAuthorities());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(person.getUserRole()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getUsername();
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
