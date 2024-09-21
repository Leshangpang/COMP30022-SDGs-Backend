package com.chen.itproject.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class JwtUtils {

    private static long time=1000*60*60;
    private static String signature="moyuer";

    public static String createJwt(HashMap<String, Object> claims){

        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                .signWith(SignatureAlgorithm.HS256, signature)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .compact();

        return jwtToken;
    }

    public static Claims parseJwt(String jwt) {

        Claims claims = Jwts.parser()
                .setSigningKey(signature)
                .parseClaimsJws(jwt)
                .getBody();

        return claims;
    }
}
