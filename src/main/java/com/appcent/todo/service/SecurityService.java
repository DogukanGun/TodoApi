package com.appcent.todo.service;


import com.appcent.todo.model.request.AuthRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
 import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class SecurityService {

    @Value("${jwt.secret}")
    private String secret;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    private byte[] generateRandomSalt(){
        //128-bit using secure PRNG

        byte [] salt = new byte[128/8];
        new Random().nextBytes(salt);

        return salt;
    }

    @SneakyThrows
    public String generateHashedPassword(String password){
        byte [] salt = generateRandomSalt();
        int iterations = 10000;
        int keyLength = 128;
        SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
        PBEKeySpec spec = new PBEKeySpec( password.toCharArray(), salt, iterations, keyLength );
        SecretKey key = skf.generateSecret( spec );
        return  Hex.encodeHexString(key.getEncoded());
    }


    public String generateToken(AuthRequest authRequest) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims).setSubject(authRequest.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}
