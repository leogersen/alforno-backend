package br.garou.com.br.alforno.util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.garou.com.br.alforno.domain.client.Client;
import br.garou.com.br.alforno.domain.restaurant.Restaurant;
import br.garou.com.br.alforno.infrastruture.web.security.LoggedUser;

public class SecurityUtils {
	
	public static LoggedUser loggedUser() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication instanceof AnonymousAuthenticationToken) {
			return null;
		}
		
		return (LoggedUser) authentication.getPrincipal();
		
	}
	
	public static Client loggedClient() {
		LoggedUser loggedUser = loggedUser();
		
		if (loggedUser == null) {
			throw new IllegalStateException("Não existe um usuário logado");
		}
		
		if (!(loggedUser.getUser() instanceof Client)) {
			throw new IllegalStateException("O usuário logado não é um Cliente");
		}
		
		return (Client) loggedUser.getUser();		
	}
	
	public static Restaurant loggedRestaurant() {
		LoggedUser loggedUser = loggedUser();
		
		if (loggedUser == null) {
			throw new IllegalStateException("Não existe um usuário logado");
		}
		
		if (!(loggedUser.getUser() instanceof Restaurant)) {
			throw new IllegalStateException("O usuário logado não é um Restaurante");
		}
		
		return (Restaurant) loggedUser.getUser();		
	}
}
