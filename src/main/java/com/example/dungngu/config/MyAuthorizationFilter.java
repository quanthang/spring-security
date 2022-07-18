package com.example.dungngu.config;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.dungngu.util.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class MyAuthorizationFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    )
        throws ServletException, IOException{
        try {
            log.infor("calling filter");
            String fullToken = request.getHeader("Authorization");
            String originalToken = fullToken.replace("Bearer","".trim());
            DecodedJWT decodedJWT = JwtUtil.getDecodedJwt(originalToken);
            String accountId =decodedJWT.getSubject();
            String username = decodedJWT.getClaim("username").asString();
            String role = decodedJWT.getClaim("role").asString();
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            log.infor("role: " + role);
            UsernamePasswordAuthenticationToken usernameToken
                    = new UsernamePasswordAuthenticationToken(accountId, null,authorities);
            SecurityContextHolder.getContext().setAuthentication(usernameToken);
        }catch (Exception ex ){
            ex.printStackTrace();
        }
        filterChain.doFilter(request, response);
    }
}
