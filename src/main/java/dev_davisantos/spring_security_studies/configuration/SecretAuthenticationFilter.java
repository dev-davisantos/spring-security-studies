package dev_davisantos.spring_security_studies.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class SecretAuthenticationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String secret = request.getHeader("x-secret");
        if (secret != null) {
            if (secret.equals("secr3t")) {
                Authentication auth = new UsernamePasswordAuthenticationToken(
                        "secretUser",
                        null,
                        List.of(new SimpleGrantedAuthority("ROLE_SPECIAL"))
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response); // This is obrigatory, if it's not called, will break the chain and the client can receive a 401 status code
    }
}
