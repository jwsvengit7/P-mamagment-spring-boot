package com.maids.backendquiz.productsmanagement.config;


import com.maids.backendquiz.productsmanagement.domain.entities.Users;
import com.maids.backendquiz.productsmanagement.domain.repository.UserRepository;
import com.maids.backendquiz.productsmanagement.exception.SecurityAppException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws SecurityAppException {
        Users user = userRepository.findUsersByEmail(email);
        if(Objects.isNull(user)){
            throw new SecurityAppException("User Not found");
        }
        return new User(user.getEmail(),user.getPassword(),getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Users user){
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));
    }
}
