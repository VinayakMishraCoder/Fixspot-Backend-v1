package com.fixspot.backendv1.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fixspot.backendv1.generalUtil.ResultWrapper;
import com.fixspot.backendv1.generalUtil.Routes;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * This filter runs before checking roles in config.
 * @see com.fixspot.backendv1.config.SecurityConfig
 * */
@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserDetailServiceImpl customUserDetailServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        System.out.println(Routes.isAlwaysPermitted(request.getRequestURI()));

        if(Routes.isAlwaysPermitted(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            writeResponseMessage(response, "Token missing or wrong format.");
            return;
        }

        String username;
        String jwt = authHeader.substring(7);

        /*
         * Throws an error when jwt is expired.
         * So capturing it and giving an apt response,
         * */
        try {
            username = jwtService.extractUsername(jwt);
        } catch (Exception e) {
            writeResponseMessage(response, "Expired session, please login again.");
            return;
        }

        try {
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = customUserDetailServiceImpl.loadUserByUsername(username);
                if (userDetails != null && jwtService.isTokenValid(jwt)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            username,
                            userDetails.getPassword(),
                            userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        } catch (Exception e) {
            writeResponseMessage(response, "Access might be denied on this route.");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private static void writeResponseMessage(HttpServletResponse response, String message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(ResultWrapper.failure(message));
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }
}