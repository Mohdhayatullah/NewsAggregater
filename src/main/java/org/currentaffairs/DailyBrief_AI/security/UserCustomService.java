package org.currentaffairs.DailyBrief_AI.security;

import lombok.RequiredArgsConstructor;
import org.currentaffairs.DailyBrief_AI.model.Client;
import org.currentaffairs.DailyBrief_AI.repository.UserRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCustomService implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = userRepo.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("This email not exist"));

        return User.builder()
                .username(client.getEmail())
                .password(client.getPassword())
                .build();
    }
}
