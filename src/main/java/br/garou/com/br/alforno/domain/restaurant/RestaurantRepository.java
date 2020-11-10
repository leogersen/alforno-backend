package br.garou.com.br.alforno.domain.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	
	public Restaurant findByEmail(String email);

}
