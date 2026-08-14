package dev_davisantos.spring_security_studies.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            CustomAuthenticationProvider customAuthProvider,
            SecretAuthenticationFilter secretAuthProvider) throws Exception {
        return http
                .authorizeHttpRequests( authorization -> {
                    authorization.requestMatchers("/public").permitAll();
                    authorization.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .authenticationProvider(customAuthProvider)
                .addFilterBefore(secretAuthProvider, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails simpleUser = User.builder()
                .username("simple-user")
                .password(passwordEncoder().encode("simple"))
                .roles("USER")
                .build();

       UserDetails adminUser = User.builder()
                .username("admin-user")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(simpleUser, adminUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
