package com.nonfallable.taskKnight.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.nonfallable.taskKnight.models.Profile;
import com.nonfallable.taskKnight.security.converters.ProfileToUserDetailsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Value("${jwt.lifeTimeInDays}")
    private long TOKEN_LIFE_TIME_IN_DAYS;

    private Algorithm algorithm;

    private JWTVerifier verifier;

    private final String EXP_CLAIM_NAME = "exp";

    private final String ID_CLAIM_NAME = "id";

    @Autowired
    private ProfileToUserDetailsConverter profileToUserDetailsConverter;

    @PostConstruct
    public void init() {
        algorithm = Algorithm.HMAC256(secret);
        verifier = JWT.require(algorithm).build();
    }

    public AccessToken decode(String token) {
        try {
            DecodedJWT decoded = verifier.verify(token);
            return new AccessToken()
                    .setToken(token)
                    .setExpiredAt(getExpiredAt(decoded))
                    .setSubject(decoded.getSubject());
        } catch (JWTVerificationException ex) {
            throw new SecurityException("JWT token is invalid", ex);
        }
    }

    private LocalDateTime getExpiredAt(DecodedJWT decoded) {
        Long exp = decoded.getClaim(EXP_CLAIM_NAME).asLong();
        return LocalDateTime.ofInstant(
                Instant.ofEpochSecond(exp),
                TimeZone.getTimeZone(ZoneOffset.UTC).toZoneId()
        );
    }

    public String getUsernameFromToken(String token) {
        return decode(token).getSubject();
    }

    public AccessToken generateToken(UUID id, String sub, LocalDateTime expiresAt) {
        return new AccessToken(
                JWT.create()
                        .withSubject(sub)
                        .withClaim(EXP_CLAIM_NAME, Timestamp.valueOf(expiresAt))
                        .withClaim(ID_CLAIM_NAME, id.toString())
                        .sign(algorithm),
                expiresAt
        );
    }

    public AccessToken generateToken(Profile userProfile) {
        UserDetails userDetails = profileToUserDetailsConverter.toUserDetails(userProfile);
        return generateToken(userProfile.getId(), userDetails.getUsername(), LocalDateTime.now().plusDays(TOKEN_LIFE_TIME_IN_DAYS));
    }
}
