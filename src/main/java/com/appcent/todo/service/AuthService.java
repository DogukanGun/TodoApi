package com.appcent.todo.service;



import com.appcent.todo.model.request.AuthRequest;
import com.appcent.todo.model.response.AuthResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import com.appcent.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.security.Security;
import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    private final AuthenticationManager authManager;
    private final SecurityService securityService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        com.appcent.todo.model.entity.User user = userRepository.getUserByUsername(s).orElseThrow(()->new NotFoundException("User not found"));
        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    public AuthResponse authenticate(AuthRequest authRequest){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
                UsernamePasswordAuthenticationToken(
                authRequest.getUsername(),
                authRequest.getPassword());
        Authentication authResult = authManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authResult);
        String token = securityService.generateToken(authRequest);
        return AuthResponse.builder()
                .authenticated(authResult.isAuthenticated())
                .token(token)
                .build();
    }



}
