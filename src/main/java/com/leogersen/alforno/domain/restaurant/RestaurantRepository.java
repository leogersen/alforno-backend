package com.leogersen.alforno.domain.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	
	public Restaurant findByEmail(String email);

	public List<Restaurant> findByNameIgnoreCaseContaining(String name);

	public List<Restaurant> findByCategories_Id(Integer categoryId);

}
