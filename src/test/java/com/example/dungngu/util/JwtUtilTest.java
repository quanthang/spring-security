package com.example.dungngu.util;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.dungngu.entity.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {
    @Test
    public void testGenerateToken(){
      /*  String acccreptToken = JwtUtil.generateToken("123123321","USER","T2009m1",7);
        System.out.println(acccreptToken);
        DecodedJWT decodedJWT = JwtUtil.getVerifier().verify(acccreptToken);
        System.out.println(decodedJWT.getSubject());
        System.out.println(decodedJWT.getIssuer());
        System.out.println(decodedJWT.getExpiresAt());*/
        Account account = Account.builder()
                .id(System.currentTimeMillis())
                .role(1)
                .username("quan")
                .passwordHash("zxv")
                .build();
        String accessToken = JwtUtil.generateTokenByAccount(account, 15*24*60*60*1000);
        System.out.println((accessToken));
    }

}