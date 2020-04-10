package com.nonfallable.taskKnight.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.TimeZone;
import java.util.UUID;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    private Algorithm algorithm;
    private JWTVerifier verifier;
    private final String EXP_CLAIM_NAME = "exp";
    private final String ID_CLAIM_NAME = "id";

    @PostConstruct
    public void init() {
        algorithm = Algorithm.HMAC256(secret);
        verifier = JWT.require(algorithm).build();
    }

    private DecodedJWT decode(String token) {
        try {
            return verifier.verify(token);
        } catch (JWTVerificationException ex) {
            throw new SecurityException("JWT token is invalid", ex);
        }
    }

    public String getUsernameFromToken(String token) {
        return decode(token).getSubject();
    }

    public String generateToken(UUID id, String sub, LocalDateTime expiresAt) {
        return JWT.create()
                .withSubject(sub)
                .withClaim(EXP_CLAIM_NAME, Timestamp.valueOf(expiresAt))
                .withClaim(ID_CLAIM_NAME, id.toString())
                .sign(algorithm);
    }

    public LocalDateTime getExp(String token) {
        Long exp = decode(token).getClaim(EXP_CLAIM_NAME).asLong();
        return LocalDateTime.ofInstant(
                Instant.ofEpochSecond(exp),
                TimeZone.getTimeZone(ZoneOffset.UTC).toZoneId()
        );
    }
}
