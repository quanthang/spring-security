package com.example.dungngu.config;

import com.example.dungngu.dto.AccountLoginDto;
import com.example.dungngu.dto.CredentialTto;
import com.example.dungngu.util.JwtUtil;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.print.attribute.standard.Media;
import javax.servlet.FilterChain;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class MyAuthenticationFilter {
    final AuthenticationManager authenticationManager;
    @Override
    public Authentication attempAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException{
        try {
            String jsonData = request.getReader().lines().collect(Collectors.joining((CharSequence) Collectors.joining()));
            Gson gson  = new Gson();
            AccountLoginDto accountLoginDto = gson.fromJson(jsonData, AccountLoginDto.class);
            UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(
                    accountLoginDto.getUsername(), accountLoginDto.getPassword()
            );
            return AuthenticationManager.authenticate(userToken);
        } catch (IOException e) {
           return null;
        }
    }
    protected void successfullAuthentication(HttpServletRequest request, HttpServletResponse response ,
                                             FilterChain chain , Authentication authResult) throws IOException, ServletException{
        User user = (User) authResult.getPrincipal()
        String accessToken = JwtUtil.generateToken(user.getUsername(),
                user.getAuthorities().iterater().next().getAuthority),
                request.getRequestURL().toString(),
        JwtUtil.ONE_DAY*7);

        String refreshToken = JwtUtil.generateToken(user.getUsername(),
                user.getAuthorities().iterater().next().getAuthority),
        request.getRequestURL().toString(),
                JwtUtil.ONE_DAY*14);
        CredentialTto credential = new CredentialTto(accessToken, refreshToken,
                JwtUtil.ONE_DAY+7 ,"basic information");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Gson gson =new Gson();
        response.getWriter().println(gson.toJson(credential));


    }

    protected void  unsuccessfullAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
        throws IOException, ServletException{
        HashMap<String ,String> error = new HashMap<>();
        error.put("message","invalid information");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Gson gson = new Gson();
        response.getWriter().println(gson.toJson(error));
    }
}
