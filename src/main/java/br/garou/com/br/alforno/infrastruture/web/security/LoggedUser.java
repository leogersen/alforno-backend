package br.garou.com.br.alforno.infrastruture.web.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.garou.com.br.alforno.domain.client.Client;
import br.garou.com.br.alforno.domain.restaurant.Restaurant;
import br.garou.com.br.alforno.domain.user.User;

@SuppressWarnings("serial")
public class LoggedUser implements UserDetails {
	
	private User user;
	private Role role;
	private Collection<? extends GrantedAuthority> roles;
	
	public LoggedUser(User user) {
		this.user = user;
		
		Role role;
		
		if(user instanceof Client) {
			role = Role.CLIENT;
			
		} else if (user instanceof Restaurant) {
			role = Role.RESTAURANT;
			
		} else {
			throw new IllegalStateException("O tipo de usuário não é válido");
		}
		this.role = role;
		this.roles = List.of(new SimpleGrantedAuthority("ROLE" + role));
				
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {

		return user.getPassword();
	}

	@Override
	public String getUsername() {

		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


	public Role getRole() {
		return role;
	}

	public User getUser() {
		return user;
	}
}
