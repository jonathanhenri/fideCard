package com.fidecard.application.seguranca.jwt;

import com.fidecard.application.seguranca.UserAuthority;
import com.fidecard.application.utils.DateUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Classe responsável por manipular o JWT
 *
 */
@Component
public abstract class AbstractJwtProvider {

    private final Logger log = LoggerFactory.getLogger(AbstractJwtProvider.class);

    @Value("${security.jwt.secret:" +
            "9SyECk96oDsTmXfogIieDI0cD/8FpnojlYSUJT5U9I/FGVmBz5oskmjOR8cbXTvoPjX+Pq/T/b1PqpHX0lYm0oCBjXWICA==}")
    private String secretKey;

    @Value("${security.jwt.validity:0}")
    private long tokenValidityInSeconds;

    private final DateUtils dateUtils;

    public AbstractJwtProvider(DateUtils dateUtils) {
        this.dateUtils = dateUtils;
    }

    public String createToken(UserAuthenticatedDto userDetails) {
        Date expiration;
        if (tokenValidityInSeconds <= 0) {
            expiration = dateUtils.addYearsToDate(dateUtils.getNow(), 100);
        } else {
            expiration = dateUtils.addSecondsToDate(dateUtils.getNow(), tokenValidityInSeconds);
        }
        return Jwts.builder()
                .setSubject(userDetails.getIdentificacao())
                .signWith(SignatureAlgorithm.HS512, this.getSecretKey())
                .setExpiration(expiration)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(this.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
        UserAuthenticatedDto userDetails = getUserDetailsByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getPermissoes().stream()
                .map(UserAuthority::new).collect(Collectors.toList()));
    }

    public boolean validateToken(String authToken) {
        if (Strings.isEmpty(authToken)) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(this.getSecretKey()).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException | SignatureException e) {
            log.info("Invalid JWT signature: {}", e.getMessage());
            return false;
        }
    }

    public byte[] getSecretKey() {
        return secretKey.getBytes();
    }

    protected abstract UserAuthenticatedDto getUserDetailsByUsername(String username);

}
