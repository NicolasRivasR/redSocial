package com.nicolas.redSocial.webtoken;

import com.nicolas.redSocial.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.crypto.SecretKey;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private static final String SKEY = "5E75578C065ED314D37A616A81A174B2EFDD9AF1C41F967FEAD02E13F4B54D3AF96B79460C8037681420F1E86BB5C6703B70F4584BAD763B6B4C73F25BE2AFE1";

    private static final Long VALIDITY = TimeUnit.MINUTES.toMillis(60); //puede que se tenga que aumentar

    public String generateToken(UserDetails user) {
        
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(VALIDITY)))
                .signWith(generaSecretKey())
                .compact();
         
    }

    private SecretKey generaSecretKey() {

        byte[] key = Base64.getDecoder().decode(SKEY);
        return Keys.hmacShaKeyFor(key);

    }
}
