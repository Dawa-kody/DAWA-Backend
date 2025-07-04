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
                                .requestMatchers(HttpMethod.POST, "/bed").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.GET, "/bed").permitAll()

                                .requestMatchers(HttpMethod.POST, "/firstaid").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.GET, "/firstaid/**").permitAll()

                                .requestMatchers(HttpMethod.POST, "/medicine/insert").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.PUT, "/medicine/update").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.DELETE, "/medicine/delete/**").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.GET, "/medicine/get").hasRole("TEACHER")

                                .requestMatchers(HttpMethod.POST, "/notice").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.GET, "/notice/**").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/notice/**").hasRole("TEACHER")

                                .requestMatchers(HttpMethod.POST, "/questionnaire/write").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.DELETE, "/questionnaire/delete").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.GET, "/questionnaire/date").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.GET, "/questionnaire/studentRecord/**").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.POST, "/questionnaire/search").hasRole("TEACHER")

                                .requestMatchers(HttpMethod.POST, "/rental/write").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.PUT, "/rental/{id}").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.GET, "/rental/allRental").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.GET, "/rental/rentalAccept").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.PUT, "/rental/rentalAccept/**").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.DELETE, "/rental/rentalCancel/**").hasRole("TEACHER")

                                .requestMatchers(HttpMethod.POST, "/rental/request").permitAll()
                                .requestMatchers(HttpMethod.GET, "/rental").permitAll()

                                .requestMatchers(HttpMethod.POST, "/user/batch").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.GET, "/user/users").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.PUT, "/user/{id}").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.POST, "/user/healthissues/**").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.DELETE, "/user/{id}").hasRole("TEACHER")
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