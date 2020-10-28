package com.myclass.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;



public class JwtFilter extends BasicAuthenticationFilter{
	private UserDetailsService userDetailsService;

	public JwtFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.userDetailsService = userDetailsService;
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//Lấy token từ header
		String tokenBearer = request.getHeader("Authorization");
		//Kiểm tra xem token có được gửi lên hay không
		if(tokenBearer != null && tokenBearer.startsWith("Bearer ")) {
			String token = tokenBearer.replace("Bearer ", "");
			//Giải mã token lấy email
			//Sử dụng email truy vấn db lấy thông tin user
			//Set thông tin user vào SecurityContext để phân quyền
			String email = Jwts.parser()
					.setSigningKey("CYBERSOFT")
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
			Authentication authentication = new  UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);	
			chain.doFilter(request, response);
		} else {
			response.sendError(401, "Chưa đăng nhập");
		}
		
			
	}

}
