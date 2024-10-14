
package com.nicolas.redSocial.webtoken;

import com.nicolas.redSocial.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;


@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private UserService userService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        
        if(authHeader == null || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            return;
        }
        
        String jwt = authHeader.substring(7);
        
        String username = jwtService.extractUsername(jwt);
        
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
        
           UserDetails userdetails = userService.loadUserByUsername(username);
           
           if(userdetails != null && jwtService.isValid(jwt)){
               UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                       username, userdetails.getPassword(), userdetails.getAuthorities()
                       
               );
               
               authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(authToken);
           
           }
            
        }
        filterChain.doFilter(request, response);
        
    }
    
}
