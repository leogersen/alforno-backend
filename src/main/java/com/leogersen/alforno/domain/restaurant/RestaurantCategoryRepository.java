package com.leogersen.alforno.domain.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RestaurantCategoryRepository extends JpaRepository<RestaurantCategory, Integer> {
	
}
