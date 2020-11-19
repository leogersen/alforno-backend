package com.leogersen.alforno.infrastruture.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.leogersen.alforno.util.SecurityUtils;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		Role role = SecurityUtils.loggedUser().getRole();
		
		if (role == Role.CLIENT) {
			response.sendRedirect("client/home");
			
		} else if (role == Role.RESTAURANT) {
			response.sendRedirect("restaurant/home");
		}else {
			throw new IllegalStateException("Erro de autentica��o");
		}
		
	}

}
