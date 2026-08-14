package dev_davisantos.spring_security_studies.configuration;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

//@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String login = authentication.getName();
        String password = (String) authentication.getCredentials();

        String loginAdmin = "admin";
        String passwordAdmin = "admin";

        if (login.equals(loginAdmin) && password.equals(passwordAdmin)) {
            return new UsernamePasswordAuthenticationToken
                    (loginAdmin, null, List.of(new SimpleGrantedAuthority("GROUP_ADMIN")));
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
