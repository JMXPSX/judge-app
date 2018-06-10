package com.judge.dredd.conf;

import static com.judge.dredd.conf.SecurityConstants.HEADER_STRING;
import static com.judge.dredd.conf.SecurityConstants.SECRET;
import static com.judge.dredd.conf.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.judge.dredd.dto.UserDTO;
import com.judge.dredd.model.AppUser;
import com.judge.dredd.repository.AppUserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;
	private AppUserRepository userRepository;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AppUserRepository userRepository) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {

		try {
			UserDTO creds = new ObjectMapper().readValue(req.getInputStream(), UserDTO.class);
			
		    List<GrantedAuthority> authorities = new ArrayList<>();
          
		    if(creds.getUserType()==5) {
		    	authorities.add(new SimpleGrantedAuthority("ADMIN"));
		    }else {
		    	authorities.add(new SimpleGrantedAuthority("USER"));
		    }
			
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(),
					creds.getPassword(),authorities));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String username = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();
		try {
			AppUser user = userRepository.findByUsername(username);
			String subject = "";
			
			if(Objects.nonNull(user)) {
				subject = user.getUserId().toString();
				// if(user.getUserType()!=5) {
				// subject = subject + "," + "USER";
				// } else {
				// subject = subject + "," + "ADMIN";
				// }
			}
			System.out.println(subject);
			String token = Jwts.builder()
					// .setSubject(((org.springframework.security.core.userdetails.User)
					// auth.getPrincipal()).getUsername())
					// .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
					.setSubject(subject)
					.signWith(SignatureAlgorithm.HS512, SECRET).setHeaderParam("typ", "JWT").compact();
			res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}