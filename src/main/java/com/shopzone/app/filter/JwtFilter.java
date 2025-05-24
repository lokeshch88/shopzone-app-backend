package com.shopzone.app.filter;

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
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.shopzone.app.service.UserService;
import com.shopzone.app.utils.JwtUtil;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
	String header=request.getHeader("Authorization");
	String token = null;
    String username = null;

    if (header != null && header.startsWith("Bearer ")) {
        token = header.substring(7);
        
        try {//try block to handle JWT-related exceptions
        username = jwtUtil.extractUsername(token);
    } catch (Exception e) {
        //  Handle any other JWT-related exceptions
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"Invalid JWT token\"}");
        return;
    }
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = userService.loadUserByUsername(username);

        if (jwtUtil.validateToken(token, userDetails)) {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }		

    chain.doFilter(request, response);
	

	}

}
