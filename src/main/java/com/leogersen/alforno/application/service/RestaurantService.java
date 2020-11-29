package com.leogersen.alforno.application.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leogersen.alforno.domain.client.Client;
import com.leogersen.alforno.domain.client.ClientRepository;
import com.leogersen.alforno.domain.restaurant.Restaurant;
import com.leogersen.alforno.domain.restaurant.RestaurantRepository;
import com.leogersen.alforno.domain.restaurant.SearchFilter;

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
	
	public List<Restaurant> search(SearchFilter filter) {
		//TODO: Setup Filters
		return restaurantRepository.findAll();
	}

}
