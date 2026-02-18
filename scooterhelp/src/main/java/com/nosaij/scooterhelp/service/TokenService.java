package com.nosaij.scooterhelp.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.nosaij.scooterhelp.domain.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Map;

@Service
public class TokenService {

    private static final String SECRET = "my-secret-key";

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withIssuer("scooterhelp-api")
                .withClaim("email", user.getEmail())
                .withClaim("name", user.getName())
                .withExpiresAt(expirationDate())
                .sign(algorithm);
    }

    private Instant expirationDate() {
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);

            return JWT.require(algorithm)
                    .withIssuer("scooterhelp-api")
                    .build()
                    .verify(token)
                    .getClaim("email").asString();
        } catch (Exception e) {
            return null;
        }
    }
}
