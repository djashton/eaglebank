package com.eaglebank.filter;

import com.eaglebank.model.User;
import com.eaglebank.service.JwtService;
import com.eaglebank.service.UserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  private static final String AUTH_HEADER = "Authorization";
  private static final String BEARER_VALUE = "Bearer ";

  @Autowired
  private JwtService jwtService;

  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    return false;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    final String authHeader = request.getHeader(AUTH_HEADER);

    String username = null;
    String jwt = null;

    if (authHeader != null && authHeader.startsWith(BEARER_VALUE)) {
      jwt = authHeader.substring(BEARER_VALUE.length());
      if (jwtService.validateToken(jwt)) {
        username = jwtService.extractUsername(jwt);
      }
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      User userDetails = userDetailsService.findUserById(username);

      if (jwtService.validateToken(jwt)) {
        UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(userDetails, null, null);
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    filterChain.doFilter(request, response);

  }
}
