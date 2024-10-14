package gym.mmt.auth.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String ACCESS_TOKEN_SECRET = "6wqm+bc/oaN/ROcNYUovZcJ4b9stPjNBYBzLZyHVCI48OoL2VrRp4FH19B4LUP/j4v8TXh1H3VuHBePO9sUpeA==";
    private static final String REFRESH_TOKEN_SECRET = "e1d1f5a3f3c6d9f4b5d0e0f8b8e0f2d7c3e1a4b6f2a4e6a2d7c4e6f8b2c7e9d1";
    private static final long ACCESS_TOKEN_EXPIRATION = 300000;
    private static final long REFRESH_TOKEN_EXPIRATION = 604800000;

    public String generateAccessToken(UserDetails user) {
        return generateToken(user, ACCESS_TOKEN_EXPIRATION, getAccessTokenSigningKey(), SignatureAlgorithm.HS512);
    }

    public String generateRefreshToken(UserDetails user) {
        return generateToken(user, REFRESH_TOKEN_EXPIRATION, getRefreshTokenSigningKey(), SignatureAlgorithm.HS256);
    }

    private String generateToken(UserDetails user, long expiration, Key signingKey, SignatureAlgorithm algorithm) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .addClaims(getClaims(user))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(signingKey, algorithm)
                .compact();
    }

    public Map<String, Object> getClaims(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", populateAuthorities(userDetails.getAuthorities()));
        return claims;
    }

    private Key getAccessTokenSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(ACCESS_TOKEN_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Key getRefreshTokenSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(REFRESH_TOKEN_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }

    private Claims extractAllClaims(String token, Key signingKey) {
        return Jwts
                .parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsernameFromAccessToken(String token) {
        return extractClaim(token, Claims::getSubject, getAccessTokenSigningKey());
    }

    public String extractUsernameFromRefreshToken(String token) {
        return extractClaim(token, Claims::getSubject, getRefreshTokenSigningKey());
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver, Key signingKey) {
        final Claims claims = extractAllClaims(token, signingKey);
        return claimsResolver.apply(claims);
    }

    public boolean isAccessTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsernameFromAccessToken(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token, getAccessTokenSigningKey());
    }

    private boolean isTokenExpired(String token, Key signingKey) {
        return extractAllClaims(token, signingKey).getExpiration().before(new Date());
    }

    public boolean isRefreshTokenValid(String token) {
        try {
            String username = extractUsernameFromRefreshToken(token);
            return username != null && !isTokenExpired(token, getRefreshTokenSigningKey());
        } catch (Exception e) {
            return false;
        }
    }
}
