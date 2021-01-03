package com.leogersen.alforno.domain.restaurant;

import org.springframework.data.jpa.repository.*;

import java.util.*;


public interface ItemMenuRepository extends JpaRepository<ItemMenu, Integer> {

    public List<ItemMenu> findByRestaurant_IdOrderByName(Integer restaurantId);

    public List<ItemMenu> findByRestaurant_IdAndHighlightOrderByName(Integer restaurantId, boolean highlight);

    public List<ItemMenu> findByRestaurant_IdAndHighlightAndCategoryOrderByName(Integer restaurantId, boolean highlight, String category);

    @Query("SELECT DISTINCT im.category FROM ItemMenu im where im.restaurant.id = ?1 ORDER BY im.category")
    public List<String> findCategories(Integer restaurantId);
	

}
