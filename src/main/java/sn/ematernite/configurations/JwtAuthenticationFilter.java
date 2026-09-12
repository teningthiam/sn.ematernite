package sn.ematernite.configurations;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sn.ematernite.services.JwtService;

import java.io.IOException;

/**
 * Filtre JWT exécuté à chaque requête HTTP.
 * Vérifie le token JWT dans le cookie ou dans le header Authorization.
 * Adapté du projet de référence webgram pour e-Maternité Sénégal.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // Chercher le JWT dans le cookie ou dans le header Authorization
        String jwt = jwtService.getJwtFromCookies(request);
        final String authHeader = request.getHeader("Authorization");

        // Skip si pas de token ou si c'est une route /auth
        if ((jwt == null && (authHeader == null
                || !authHeader.startsWith("Bearer ")))
                || request.getRequestURI().contains("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Récupérer le JWT depuis le header si pas dans le cookie
        if (jwt == null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
        }

        final String telephone = jwtService.extractUserName(jwt);

        if (StringUtils.isNotEmpty(telephone)
                && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails =
                    this.userDetailsService.loadUserByUsername(telephone);

            if (jwtService.isTokenValid(jwt, userDetails)) {
                SecurityContext context =
                        SecurityContextHolder.createEmptyContext();

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request));

                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
        }

        filterChain.doFilter(request, response);
    }
}