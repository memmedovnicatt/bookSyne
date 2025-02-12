package com.example.booksyne.configuration;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers(permitAllUrls).permitAll()
                                .requestMatchers(HttpMethod.GET, "/bags/{id}").hasRole("USER")//++
                                .requestMatchers(HttpMethod.GET, "/gifts/{id}").hasRole("USER")//++
                                .requestMatchers(HttpMethod.GET, "/gifts").hasRole("USER")//++

                                .requestMatchers(HttpMethod.GET, "/magazines/{id}").hasRole("USER") //++

                                .requestMatchers(HttpMethod.POST, "/bags").hasRole("ADMIN")//++
                                .requestMatchers(HttpMethod.DELETE, "/bags/{id}").hasRole("ADMIN")//++
                                .requestMatchers(HttpMethod.PATCH, "/bags/{id}").hasRole("ADMIN")//++
                                .requestMatchers(HttpMethod.POST, "/bags/{id}").hasRole("ADMIN")//++
                                .requestMatchers(HttpMethod.DELETE, "/books/{id}").hasRole("ADMIN")//++
                                .requestMatchers(HttpMethod.POST, "/gifts/").hasRole("ADMIN")//++
                                .requestMatchers(HttpMethod.PATCH, "/gifts/{id}").hasRole("ADMIN")//++

                                .requestMatchers(HttpMethod.POST, "/magazines/").hasRole("ADMIN")//++
                                .requestMatchers(HttpMethod.PATCH, "/magazines/{id}").hasRole("ADMIN")//++
                                .requestMatchers(HttpMethod.DELETE, "/magazines/{id}").hasRole("ADMIN") //++
                                .requestMatchers(HttpMethod.POST, "/magazines/{id}").hasRole("ADMIN")//++
                                .requestMatchers(HttpMethod.DELETE, "/authors/{id}").hasRole("ADMIN")//++

                                .requestMatchers(permitUserUrls).hasRole("USER")//
                                .requestMatchers(permitAdminUrls).hasRole("ADMIN")//
                                .anyRequest().authenticated()


                ).exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint((request, response, authException) ->
                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED)
                        )
                        .accessDeniedHandler((request, response, accessDeniedException) ->
                                response.setStatus(HttpServletResponse.SC_FORBIDDEN)
                        )
                );
        return http.build();
    }

    static String[] permitAllUrls = {
            "/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/auth/**",
            "/users/**",
            "/api/v1/**"
    };

    static String[] permitUserUrls = {
            "/authors/{authorName}",//++
            "/bags/{search}",//++
            "/bags",//++
            "/books/author/{authorId}",//++
            "/books/language/{language}",//++
            "/books/id/{id}",//++
            "/gifts/search/{address}",//++
            "/magazines",//++
            "/payments",//++
            "/cards/**",//
            "/favourites/**",//
            "/baskets/**",//
            "/basket-items/**"//++

    };

    static String[] permitAdminUrls = {
            "/authors",//++
            "/authors/{authorName}",//++
            "/books",//++
            "/suppliers/**",//++
            "/publishers/**",//++
            "/productEntities/**"//++

    };
}