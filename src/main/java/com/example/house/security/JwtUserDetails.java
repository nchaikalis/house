package com.example.house.security;

import com.example.house.entity.Person;
import com.example.house.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetails implements UserDetailsService {

    @Autowired
    private PersonRepository repoUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = repoUser.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getUserPassword())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}
