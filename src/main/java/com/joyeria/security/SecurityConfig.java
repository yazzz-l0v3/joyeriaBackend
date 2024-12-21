package com.joyeria.security;

import java.util.Collections;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
	private JwtFilter jwtFilter;
	
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    	httpSecurity.csrf(csrf->csrf.disable())
    		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    		.authorizeHttpRequests(http->{
    			http.requestMatchers("/api/usuario/login").permitAll();
    			http.requestMatchers("/api/usuario/registro").permitAll();
    			http.requestMatchers("/api/producto/listar").permitAll();
    			http.requestMatchers("/api/producto/registrar").permitAll();
    			http.requestMatchers("/api/marca/listar").permitAll();
                http.requestMatchers("/api/producto/actualizar/**").permitAll();
                http.requestMatchers("/api/producto/eliminar").permitAll();
                http.anyRequest().authenticated();
    		})
    		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    	return httpSecurity.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
