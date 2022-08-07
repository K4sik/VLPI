package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.service.impl;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.User;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) {
            return org.springframework.security.core.userdetails.User
                    .builder()
                    .username(user.get().getEmail())
                    .password(user.get().getPassword())
                    .authorities(user.get().getUserRole())
                    .build();
        }

        throw new UsernameNotFoundException("User with email '" + email + "' not found");
    }

}
