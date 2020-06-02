package com.example.demo.jwt;

import com.example.demo.repository.UserRepository;
import com.example.demo.security.UserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;


public class UsernameAndPasswordAuthentication extends UsernamePasswordAuthenticationFilter {


    private AuthenticationManager authenticationManager;


    public UsernameAndPasswordAuthentication(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }



    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {


        JwtViewModel model=null;

        try{

            model= new ObjectMapper().readValue(request.getInputStream(),JwtViewModel.class);


        }catch (IOException e){
            throw new RuntimeException(e);
        }


        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                model.getLogin(),model.getPassword(),new ArrayList<>());

        Authentication authentication=authenticationManager.authenticate(authenticationToken);

        return authentication;



    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {


        UserDetails userDetails = (UserDetails) authResult.getPrincipal();




        String token= Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setExpiration(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_DATE))
                .signWith(Keys.hmacShaKeyFor(JwtProperties.SECRET_KEY.getBytes()))
                .compact();

        response.addHeader(JwtProperties.HEADER,JwtProperties.TOKEN_PREFIX+token);
    }



}
