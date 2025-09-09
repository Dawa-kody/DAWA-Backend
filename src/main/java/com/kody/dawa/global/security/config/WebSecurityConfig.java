package com.kody.dawa.global.security.config;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kody.dawa.global.handler.CustomAccessDeniedHandler;
import com.kody.dawa.global.filter.JwtExceptionFilter;
import com.kody.dawa.global.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtFilter jwtFilter;
    private final JwtExceptionFilter jwtExceptionFilter;
    private final ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(corsCustomizer -> corsCustomizer.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers(HttpMethod.POST, "/api/bed").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.GET, "/api/bed").permitAll()

                                .requestMatchers(HttpMethod.POST, "/api/firstaid").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.GET, "/api/firstaid/*").permitAll()

                                .requestMatchers(HttpMethod.POST, "/api/medicine/insert").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.PUT, "/api/medicine/update").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.DELETE, "/api/medicine/delete/*").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.GET, "/api/medicine/get").hasRole("TEACHER")

                                .requestMatchers(HttpMethod.POST, "/api/notice").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.GET, "/api/notice/*").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/api/notice/*").hasRole("TEACHER")

                                .requestMatchers(HttpMethod.POST, "/api/questionnaire/write").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.DELETE, "/api/questionnaire/delete").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.GET, "/api/questionnaire/date").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.GET, "/api/questionnaire/studentRecord/*").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.POST, "/api/questionnaire/search").hasRole("TEACHER")

                                .requestMatchers(HttpMethod.POST, "/api/rental/write").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.PUT, "/api/rental/{id}").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.GET, "/api/rental/allRental").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.GET, "/api/rental/rentalAccept").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.PUT, "/api/rental/rentalAccept/*").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.DELETE, "/api/rental/rentalCancel/*").hasRole("TEACHER")

                                .requestMatchers(HttpMethod.POST, "/api/rental/request").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/rental").permitAll()

                                .requestMatchers(HttpMethod.POST, "/api/user/batch").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.GET, "/api/user/users").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.PUT, "/api/user/{id}").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.POST, "/api/user/healthissues/*").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.DELETE, "/api/user/{id}").hasRole("TEACHER")
                                .anyRequest().permitAll()
                )
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFilter, JwtFilter.class)
                .exceptionHandling(handlingConfigurer ->
                        handlingConfigurer.accessDeniedHandler(new CustomAccessDeniedHandler(objectMapper))
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:3000", "https://www.gsm-dawa.com"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList(
                "Authorization", "Content-Type",
                "ngrok-skip-browser-warning", "CF-Access-Client-Id", "CF-Access-Client-Secret"
        ));
        config.setExposedHeaders(List.of("Set-Cookie","Content-Disposition", "Content-Type", "Cache-Control"));
        config.setAllowCredentials(true);
        config.setMaxAge(86400L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
