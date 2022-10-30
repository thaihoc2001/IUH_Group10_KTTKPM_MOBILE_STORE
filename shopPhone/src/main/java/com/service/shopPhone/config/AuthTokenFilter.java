package com.service.shopPhone.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.service.shopPhone.service.MyUserDetailsService;

public class AuthTokenFilter  extends OncePerRequestFilter{


    @Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String userId = getUserId(request);
            if (userId != null) {
      
              UserDetails userDetails = userDetailsService.loadUserByUsername(userId);

              UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                  userDetails.getAuthorities());
              authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      
              SecurityContextHolder.getContext().setAuthentication(authentication);
            }
          } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
          }
      
          filterChain.doFilter(request, response);
        }
      
        private String getUserId(HttpServletRequest request) {
          String headerAuth = request.getHeader("api-key");
      
          if (StringUtils.hasText(headerAuth)) {
            return headerAuth;
          }
      
          return null;
    }
    
}
