package com.appcent.todo.configuration;

import com.appcent.todo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyAuthProvider  implements AuthenticationProvider {

    private final AuthService authService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String providedUsername = authentication.getPrincipal().toString();
        UserDetails user = authService.loadUserByUsername(providedUsername);

        return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        System.out.println("\nIn MyAuthProvider.supports(): ");
        System.out.println("Checking whether MyAuthProvider supports Authentication type\n");
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
