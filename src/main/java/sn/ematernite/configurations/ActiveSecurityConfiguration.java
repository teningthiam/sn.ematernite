package sn.ematernite.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * Configuration de sécurité ACTIVE.
 * Activée quand securite.active=false dans application.yml
 * (false = sécurité active, true = tout ouvert pour les tests)
 */
@ConditionalOnProperty(
        name = "securite.active",
        havingValue = "false",
        matchIfMissing = false)
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class ActiveSecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final Http401UnauthorizedEntryPoint unauthorizedEntryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    private static final Long MAX_AGE = 3600L;
    private static final int CORS_FILTER_ORDER = -102;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(unauthorizedEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler))
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                "/api/auth/**",
                                "/v2/api-docs",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // Routes femme enceinte
                        .requestMatchers("/api/grossesses/**")
                        .hasAnyRole("FEMME_ENCEINTE", "ADMINISTRATEUR")
                        .requestMatchers("/api/carnets/**")
                        .hasAnyRole("FEMME_ENCEINTE", "SAGE_FEMME",
                                "MEDECIN", "ADMINISTRATEUR")
                        .requestMatchers("/api/notifications/**")
                        .hasAnyRole("FEMME_ENCEINTE", "ADMINISTRATEUR")

                        // Routes sage-femme / médecin
                        .requestMatchers("/api/consultations/**")
                        .hasAnyRole("SAGE_FEMME", "MEDECIN", "ADMINISTRATEUR")
                        .requestMatchers("/api/bebes/**")
                        .hasAnyRole("SAGE_FEMME", "MEDECIN", "ADMINISTRATEUR")
                        .requestMatchers("/api/vaccinations/**")
                        .hasAnyRole("SAGE_FEMME", "MEDECIN", "ADMINISTRATEUR")

                        // Routes administrateur
                        .requestMatchers("/api/structures/**")
                        .hasRole("ADMINISTRATEUR")
                        .requestMatchers("/api/utilisateurs/**")
                        .hasRole("ADMINISTRATEUR")
                        .requestMatchers("/api/conseils/**")
                        .hasAnyRole("FEMME_ENCEINTE", "ADMINISTRATEUR")

                        // Sync accessible à tous connectés
                        .requestMatchers("/api/sync/**").authenticated()

                        .anyRequest().authenticated()
                )
                .sessionManagement(manager ->
                        manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        // Flutter mobile — autorise toutes les origines
        config.addAllowedOriginPattern("*");
        config.setAllowedHeaders(Arrays.asList(
                HttpHeaders.AUTHORIZATION,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT));
        config.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.PATCH.name(),
                HttpMethod.DELETE.name()));
        config.setMaxAge(MAX_AGE);

        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean =
                new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(CORS_FILTER_ORDER);
        return bean;
    }
}