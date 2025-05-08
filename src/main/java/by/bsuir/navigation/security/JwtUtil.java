package by.bsuir.navigation.security;

import by.bsuir.navigation.dto.auth.TokenDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "e86d2d40f6cb4eaa93458daced31cfdb84c5fd0384ddffd5c6d8b3e03ea982dcf43966e7a67d084823d02377cee438395a3f829eeb31079f416a596a39e896d64b6d25b623817fec6c1d86d5306f0a1940b9f8e9055adbb28517e8311c29953bef2e72d5c1205cbd7b3e558c9287f56dcdd2494ff809fc87c961256c08fc4146418b6458c9624a14aa8ece17d434fa0a5ea760b10c4ba6f7e34c4bc9466d2bd2339fce43d117c040c25dc64c79be827e7172c665592f8a205759e07ee53deec9c18638dd98513fe1ad1896ff927bf9b117c69c7e99872898a79e60dc253691df4dc19287552019253ff5a640a35c85cbbe4dd01ebc32ee6a4c47f8ff596e780f";
    private final long EXPIRATION = 1000 * 60 * 60;

    public TokenDTO generateToken(String username, String roles) {
        return new TokenDTO(Jwts.builder()
                .setSubject(username)
                .claim("roles",roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET).compact());
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }
}

