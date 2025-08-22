package com.proyecto.security;

import com.proyecto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //Roles actuales ADMINISTRADOR, MEDICO, RECEPCIONISTA
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/paciente/**").hasAnyRole("ADMINISTRADOR", "MEDICO", "RECEPCIONISTA")
                        .requestMatchers("/medico/**").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers("/atencion/**").hasAnyRole("ADMINISTRADOR", "MEDICO", "RECEPCIONISTA")
                        .requestMatchers("/historialMedico/**").hasAnyRole("ADMINISTRADOR", "MEDICO")

                        .requestMatchers("/alergia/**").hasAnyRole("ADMINISTRADOR", "MEDICO", "RECEPCIONISTA")
                        .requestMatchers("/radiografia/**").hasAnyRole("ADMINISTRADOR", "MEDICO", "RECEPCIONISTA")
                        .requestMatchers("/tratamientoMedico/**").hasAnyRole("ADMINISTRADOR", "MEDICO", "RECEPCIONISTA")
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/security/login")
                        .defaultSuccessUrl("/security/index", true)
                        .failureUrl("/security/login?error")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/security/logout")
                        .logoutSuccessUrl("/security/login?logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .exceptionHandling(exception -> exception
                .accessDeniedPage("/security/accessDenied"));

        return http.build();
    }

    @Autowired
    private UsuarioService usuarioService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(usuarioService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }


}
