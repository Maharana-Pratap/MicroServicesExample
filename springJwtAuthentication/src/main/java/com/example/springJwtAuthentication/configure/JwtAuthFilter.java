package com.example.springJwtAuthentication.configure;

import java.io.IOException;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// getting token from request
		String userToken = request.getHeader("Authorization");
		String userName = null;
		String token = null;
		
		if(null != userToken && userToken.startsWith("Bearer")) {
			
			token = userToken.substring(7);
			
			try {
				userName = jwtUtil.extractUsername(userToken);
			} catch (ExpiredJwtException e) {
				System.out.println("token has been expired!!!");
				e.getLocalizedMessage();
			
			} catch (Exception e) {
				System.out.println("Invalid token found");
				e.getLocalizedMessage();
			}
		}
		
		// once get the token
		// then now go to validate the token
		if(null != token && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService
					.loadUserByUsername(userName);
			
			if(jwtUtil.validateToken(token, userDetails)) {
				// everything is ok now
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken 
				= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
				.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		
		filterChain.doFilter(request, response);		
	}

}
