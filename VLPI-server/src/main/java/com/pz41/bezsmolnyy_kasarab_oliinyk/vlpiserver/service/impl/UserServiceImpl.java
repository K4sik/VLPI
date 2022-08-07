package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.service.impl;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.config.jwt.JWTTokenProvider;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.User;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.enums.UserRole;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.exceptions.CustomServiceException;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.repository.UserRepository;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.constants.ErrorMessages.RECORD_ALREADY_EXISTS;
import static com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.constants.ErrorMessages.RECORD_NOT_FOUND;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void save(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new CustomServiceException(RECORD_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
        }
        user.setUserRole(UserRole.ROLE_STUDENT);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        try {
            return userRepository.getById(id);
        } catch(NoSuchElementException e) {
            throw new CustomServiceException(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElse(null);
    }

    @Override
    public String signin(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            return jwtTokenProvider.createToken(email,user.get().getUserRole());
        }
        return null;
    }

}
