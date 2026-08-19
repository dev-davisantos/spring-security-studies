package dev_davisantos.spring_security_studies.configuration;

import dev_davisantos.spring_security_studies.model.UserEntity;
import dev_davisantos.spring_security_studies.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {


    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;

    private final UserDetailsService inMemoryUserDetailsService;

    public CustomAuthenticationProvider(
            @Qualifier("userDetailsServiceImpl")UserDetailsServiceImpl userDetailsService,
            PasswordEncoder passwordEncoder,
            @Qualifier("inMemoryUserDetailsService")UserDetailsService inMemoryUserDetailsService) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.inMemoryUserDetailsService = inMemoryUserDetailsService;
    }

    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String password = (String) authentication.getCredentials();

        if (authentication.getName().equals("admin-user")) { // Used to use inMemory admin-user created, while it's using h2-database
            UserDetails adminUser = inMemoryUserDetailsService.loadUserByUsername("admin-user");
            if (passwordEncoder.matches(password, adminUser.getPassword())) {
                return new UsernamePasswordAuthenticationToken(adminUser, null, adminUser.getAuthorities());
            }
        }

        UserEntity user = userDetailsService.loadUserByUsername(authentication.getName());
        boolean matches = passwordEncoder.matches(password, user.getPassword());

        if (matches) {
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        }


        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
