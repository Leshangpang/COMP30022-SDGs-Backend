package com.chen.itproject.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class JwtUtils {

    // JWT expiration time set to 1 hour (in milliseconds)
    private static long time=1000*60*60;

    // Secret signature key used for signing the JWT
    private static String signature="moyuer";

    /**
     * Creates a JWT token with the given claims
     * @param claims A HashMap containing the claims to be included in the JWT
     * @return The generated JWT token as a String
     */
    public static String createJwt(HashMap<String, Object> claims){

        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                .signWith(SignatureAlgorithm.HS256, signature) // Sign the JWT with the HS256 algorithm and the secret key
                .setClaims(claims)                             // Set the claims/payload of the JWT
                .setExpiration(new Date(System.currentTimeMillis() + time)) // Set the expiration time
                .compact();                                    // Build the JWT token

        return jwtToken;
    }

    /**
     * Parses a JWT token to extract the claims
     * @param jwt The JWT token to be parsed
     * @return Claims object containing the information extracted from the token
     */
    public static Claims parseJwt(String jwt) {

        // Parse the JWT token using the secret key to extract the claims
        Claims claims = Jwts.parser()
                .setSigningKey(signature)     // Set the signing key used to validate the token
                .parseClaimsJws(jwt)          // Parse the token
                .getBody();                   // Extract the claims from the token

        return claims;  // Return the extracted claims
    }
}
