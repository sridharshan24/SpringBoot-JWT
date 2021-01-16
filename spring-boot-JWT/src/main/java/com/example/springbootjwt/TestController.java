package com.example.springbootjwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	MyUserDetailsService userDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@RequestMapping(path = "/hello")
	public String hello()
	{
		return "Hello from controller........";
	}
	
	@RequestMapping(path = "/authenticate")
	public AuthenticationResponse createJWTToken(@RequestBody AuthenticationRequest request) throws Exception
	{
		try
		{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		}catch (BadCredentialsException e)
		{
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		String jwtToken = jwtUtil.generateToken(userDetails);
		
		AuthenticationResponse response = new AuthenticationResponse();
		response.setJwt(jwtToken);
		
		return response;
		
	}

}
