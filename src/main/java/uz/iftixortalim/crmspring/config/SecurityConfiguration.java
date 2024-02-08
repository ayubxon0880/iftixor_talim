package uz.iftixortalim.crmspring.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;


import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(cors -> {
            cors.configurationSource(request -> {
                CorsConfiguration configuration = new CorsConfiguration();
//                configuration.setAllowedOrigins(List.of("http://localhost:3000/"));
                configuration.setAllowedOrigins(List.of("https://iftixor-talim.uz/"));
                configuration.setAllowedMethods(List.of("*"));
                configuration.setAllowCredentials(true);
                configuration.setAllowedHeaders(List.of("*"));
                return configuration;
            });
        });

        http.authorizeHttpRequests(
                auth -> auth
//                        .anyRequest()
//                        .permitAll()
                        .requestMatchers("/api/v1/auth/**","/api/test/**","/api-docs","/api/v1/mail/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
        );

        http.sessionManagement(se -> se.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authenticationProvider(authenticationProvider);
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
