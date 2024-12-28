package com.pbio.playbeach.services;

import com.pbio.playbeach.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.withUsername(user.getUsername())
                .password("")
                .authorities("")
                .build();
    }
}