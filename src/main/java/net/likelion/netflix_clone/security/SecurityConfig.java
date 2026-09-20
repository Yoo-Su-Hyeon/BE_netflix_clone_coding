package net.likelion.netflix_clone.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter
    ) {
        this.jwtAuthenticationFilter =
                jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        // 회원가입 / 로그인 / Swagger
                        .requestMatchers(
                                "/api/users/signup",
                                "/api/users/login",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // 콘텐츠 등록 - ADMIN
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/contents/**"
                        ).hasRole("ADMIN")

                        // 콘텐츠 수정 - ADMIN
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/contents/**"
                        ).hasRole("ADMIN")

                        // 콘텐츠 삭제 - ADMIN
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/contents/**"
                        ).hasRole("ADMIN")

                        // 콘텐츠 조회 - USER, ADMIN
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/contents/**"
                        ).hasAnyRole("USER", "ADMIN")

                        // 장르 등록 - ADMIN
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/genres/**"
                        ).hasRole("ADMIN")

                        // 장르 수정 - ADMIN
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/genres/**"
                        ).hasRole("ADMIN")

                        // 장르 삭제 - ADMIN
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/genres/**"
                        ).hasRole("ADMIN")

                        // 장르 조회 - USER, ADMIN
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/genres/**"
                        ).hasAnyRole("USER", "ADMIN")

                        // 나머지는 로그인 필요
                        .anyRequest()
                        .authenticated()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}