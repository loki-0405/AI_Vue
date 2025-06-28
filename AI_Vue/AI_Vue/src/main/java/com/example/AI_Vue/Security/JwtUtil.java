package com.example.AI_Vue.Security;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import static java.security.KeyRep.Type.SECRET;
import static javax.crypto.Cipher.SECRET_KEY;

@Component
public class JwtUtil {

        private final String Skey = "mySuperSecretKeyForJWT12345678901234567890";

    private final Key key =  Keys.hmacShaKeyFor(Skey.getBytes());

        private final long EXPIRATION = 86400000; // 1 day

        public String generateToken(String username) {
            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                    .signWith(SignatureAlgorithm.HS256, key)
                    .compact();
        }

        public String extractUsername(String token) {
            return Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }

        public boolean isTokenValid(String token, UserDetails userDetails) {
            String username = extractUsername(token);
            return username.equals(userDetails.getUsername());
        }
    }

