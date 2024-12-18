package com.timesheet.app.security.token;

import com.timesheet.app.constants.AuthConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class TokenUtils {

    @Value("${jwt_secret_key}")
    private String JWT_SECRET;

    public String generateToken(User user){
        return Jwts
                .builder()
                .subject(user.getUsername())
                .claim("role", user.getAuthorities().toString())
                .issuedAt(new Date())
                .expiration(createExpirationDate())
                .signWith(key())
                .compact();
    }

    public String getUsername(String token){
        return Jwts
                .parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public String getRole(String token){
       return Jwts
                .parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role").toString();
    }

    public Date getExpirationDate(String token){
        return Jwts
                .parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        String username = getUsername(token);
        return (username != null &&
                username.equals(userDetails.getUsername()) &&
                !isTokenExpired(token));

    }

    private boolean isTokenExpired(String token){
        return getExpirationDate(token).before(new Date());
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_SECRET));
    }

    private Date createExpirationDate(){
        return new Date(new Date().getTime() + AuthConstants.JWT_EXPIRATION_DATE);
    }

}
