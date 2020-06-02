package com.example.demo.jwt;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.UserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final UserRepository userRepository;

    public JwtAuthorizationFilter (AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain chain) throws IOException, ServletException {


        String header = request.getHeader(JwtProperties.HEADER);

        if(header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)){
            chain.doFilter(request,response);
        }

        Authentication authentication = getUsernamePasswordAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);



        chain.doFilter(request,response);


    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {

        String token = request.getHeader(JwtProperties.HEADER);

        if(token !=null){
            Claims claims= Jwts.parser()
                    .setSigningKey(JwtProperties.SECRET_KEY.getBytes())
                    .parseClaimsJws(token.replace(JwtProperties.TOKEN_PREFIX,""))
                    .getBody();

            String username=claims.getSubject();


            if(username != null){


                User user = userRepository.findByLogin(username);

                String userRole = user.getRole();

                UserDetails userDetails = new UserDetails(user);

                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(
                        username,null,userDetails.getAuthorities()
                );
                return authenticationToken;


            }

        }



return null;

    }
}
