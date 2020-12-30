package com.leogersen.alforno.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leogersen.alforno.domain.client.Client;
import com.leogersen.alforno.domain.client.ClientRepository;
import com.leogersen.alforno.domain.restaurant.Restaurant;
import com.leogersen.alforno.domain.restaurant.RestaurantRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	
	@Transactional
	public void saveClient(Client client) throws ValidationException {
		
		if (!validateEmail(client.getEmail(), client.getId())) {
			throw new ValidationException("O e-mail já está cadastrado");
		
		}
		
		if (client.getId() != null) {
			Client clientDB = clientRepository.findById(client.getId()).orElseThrow();
			client.setPassword(clientDB.getPassword());
			
		}else {
			client.encryptPassword();
		}
		
		
		clientRepository.save(client);
	}
	
	private boolean validateEmail(String email, Integer id)  {
		Restaurant restaurant = restaurantRepository.findByEmail(email);
		
		if (restaurant != null) {
			return false;
		}
		
		Client client = clientRepository.findByEmail(email);
		
		if (client != null) { 
			if (id == null){
		     return false;
			}
			if(!client.getId().equals(id)) {
				return false;
			}
				
		}
			return true;
		
}
}