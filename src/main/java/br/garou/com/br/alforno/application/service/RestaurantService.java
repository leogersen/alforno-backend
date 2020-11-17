package br.garou.com.br.alforno.application.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.garou.com.br.alforno.domain.client.Client;
import br.garou.com.br.alforno.domain.client.ClientRepository;
import br.garou.com.br.alforno.domain.restaurant.Restaurant;
import br.garou.com.br.alforno.domain.restaurant.RestaurantRepository;

@Service
public class RestaurantService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ImageService imageService;
	
	@Transactional
	public void saveRestaurant(Restaurant restaurant) throws ValidationException {
		
		if (!validateEmail(restaurant.getEmail(), restaurant.getId())) {
			throw new ValidationException("O e-mail já está cadastrado");
		 
		}
		
		if (restaurant.getId() != null) {
			Restaurant restaurantDB = restaurantRepository.findById(restaurant.getId()).orElseThrow();
			restaurant.setPassword(restaurantDB.getPassword());
			
		}else {
			restaurant.encryptPassword();
			restaurant = restaurantRepository.save(restaurant);
			restaurant.setLogoFileName();
			imageService.uploadLogo(restaurant.getLogoFile(), restaurant.getLogo());
			
		}		
		

	}
	
	private boolean validateEmail(String email, Integer id)  {
		Client client = clientRepository.findByEmail(email);
		
		if (client != null) {
			return false;
		}
		
		
		
		
		Restaurant restaurant = restaurantRepository.findByEmail(email);
		
		if (restaurant != null) { 
			if (id == null){
		     return false;	
		     
			}
			
			if(!restaurant.getId().equals(id)) {
				return false;
			} 
				
		}
			
			return true;
		
}

}
