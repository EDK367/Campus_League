package com.umesdnd.CampusLeague.config;

import com.umesdnd.CampusLeague.service.Token.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // para login, para registro de equipos y sus jugadores, para lectura de torneos. Las rutas estan libres
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/equipo").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/equipo").permitAll()
                        .requestMatchers(HttpMethod.POST, "/jugador").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/jugador").permitAll()
                        .requestMatchers(HttpMethod.GET, "/torneo/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/deporte/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/posicion-jugador/**").permitAll()
                        // para documentacion con sweager & sweagger-ui
                        // documentacion: http://localhost:8080/campus-league/api/swagger-ui/index.html
                        .requestMatchers(
                                "/v1/api/**",
                                "/v2/api-docs",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-resourgces",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // todas las demas rutas y operaciones estan protegidas por default
                        .requestMatchers(HttpMethod.POST, "/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/**").authenticated()
                ).sessionManagement(sessionManager ->
                        sessionManager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(ex -> ex
                    .authenticationEntryPoint((request, response, authException) -> {
                        System.out.println(authException.getMessage());
                        if (authException.getMessage().equalsIgnoreCase("Bad credentials")){
                            response.setStatus(400);
                        } else {
                            response.setStatus(401);
                        }
                }))
                .build();
    }


}
