package com.aliabou.secuirty.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.aliabou.secuirty.entities.Permission.ADMIN_READ;
import static com.aliabou.secuirty.entities.Role.ADMIN;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private static final String[] WHITE_LIST_URL = {"/auth/**"};
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers("/api/employee/**").hasAnyRole(ADMIN.name())
                                .requestMatchers(GET, "/api/employee/**").hasAnyAuthority(ADMIN_READ.name())
                                .requestMatchers("/api/dep/**").hasAnyRole(ADMIN.name())
                                .requestMatchers(GET, "/api/dep/**").hasAnyAuthority(ADMIN_READ.name())
                                .requestMatchers("/api/pro/**").hasAnyRole(ADMIN.name())
                                .requestMatchers(GET, "/api/pro/**").hasAnyAuthority(ADMIN_READ.name())
                                .anyRequest()
                                .authenticated()

                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();

    }
}
