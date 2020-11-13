package br.garou.com.br.alforno.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.garou.com.br.alforno.domain.restaurant.Restaurant;
import br.garou.com.br.alforno.domain.restaurant.RestaurantRepository;

@Service
public class RestaurantService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
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
			//TODO Upload
			
		}		
		

	}
	
	private boolean validateEmail(java.lang.String string, Integer id)  {
		Restaurant restaurant = restaurantRepository.findByEmail(string);
		
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
