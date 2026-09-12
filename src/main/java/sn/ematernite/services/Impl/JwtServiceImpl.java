package sn.ematernite.services.Impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;
import sn.ematernite.services.JwtService;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration:2592000000}")
    private long expiration;

    private static final String JWT_COOKIE_NAME = "ematernite-jwt";

    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getCle())
                .compact();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    @Override
    public ResponseCookie generateJwtCookie(String jwt) {
        return ResponseCookie.from(JWT_COOKIE_NAME, jwt)
                .path("/api")
                .maxAge(24 * 60 * 60) // 24 heures
                .httpOnly(true)
                .build();
    }

    @Override
    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, JWT_COOKIE_NAME);
        return cookie != null ? cookie.getValue() : null;
    }

    @Override
    public ResponseCookie getCleanJwtCookie() {
        return ResponseCookie.from(JWT_COOKIE_NAME, "")
                .path("/api")
                .build();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token,
                               Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getCle())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getCle() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}