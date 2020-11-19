package br.garou.com.br.alforno.infrastruture.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.garou.com.br.alforno.domain.client.ClientRepository;
import br.garou.com.br.alforno.domain.restaurant.RestaurantRepository;
import br.garou.com.br.alforno.domain.user.User;

@Service
public class UserDetailsSerciveImpl implements UserDetailsService {

	
	@Autowired
	private ClientRepository clientRepository;	
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = clientRepository.findByEmail(username);
		
		if(user == null) {
			user = restaurantRepository.findByEmail(username);
			
			if (user == null) {
				throw new UsernameNotFoundException(username);
			}
		}
		
		return new LoggedUser(user);
	}

}
