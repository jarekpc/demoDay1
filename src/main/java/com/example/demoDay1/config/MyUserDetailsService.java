package com.example.demoDay1.config;

import com.example.demoDay1.app.Person;
import com.example.demoDay1.app.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    public MyUserDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("search username " + username);
        Person p = personRepository.findByUsername(username);
        if (p == null)
            throw new UsernameNotFoundException(username);
        else
            return new MyUserPrincipal(p);
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
