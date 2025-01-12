package com.example.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Service
public class JwttUtil {
    private final String secretKey = "xQq1wUJ4Dd9V5EelWKdX9U7inIcnLGypJyP8P0ORKjQp"; // Use a strong, fixed secret key
    private final long expirationTime = 60 * 60 * 1000 ;// 1 hour in milliseconds

    public String generateToken(String username)
    {
        Map<String,Object>claims=new HashMap<>();
        claims.put("uniqueId", UUID.randomUUID().toString());
        // setAuthenticatedUser(username);
        return Jwts.builder()
                .claims()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis())).
                expiration(new Date(System.currentTimeMillis()+expirationTime))
                .and()
                .signWith(getKey())
                .compact();
    }

    public String extract(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validate(String token) {
        try {
            final String username = extract(token);
            System.out.println(username);
            return (username != null && !isTokenExpired(token));
        } catch (Exception e) {
            return false;
        }
    }
    private boolean isTokenExpired(String token) {
        final Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}