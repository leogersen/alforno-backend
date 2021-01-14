package com.leogersen.alforno.application.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.leogersen.alforno.domain.client.Client;
import com.leogersen.alforno.domain.client.ClientRepository;
import com.leogersen.alforno.domain.restaurant.ItemMenu;
import com.leogersen.alforno.domain.restaurant.ItemMenuRepository;
import com.leogersen.alforno.domain.restaurant.Restaurant;
import com.leogersen.alforno.domain.restaurant.RestaurantCategory;
import com.leogersen.alforno.domain.restaurant.RestaurantCategoryRepository;
import com.leogersen.alforno.domain.restaurant.RestaurantRepository;
import com.leogersen.alforno.util.StringUtils;

@Component
public class InserDataForTesting {
	
	
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private RestaurantCategoryRepository restaurantCategoryRepository;
	
	
	@Autowired
	private ItemMenuRepository itemMenuRepository;
	
	
	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		clients();
		Restaurant[] restaurants = restaurants();
		itensMenu(restaurants);
		
		
	}
	
	private Restaurant[] restaurants() {
		List<Restaurant> restaurants = new ArrayList<>();
		
		RestaurantCategory lanchesCategory = restaurantCategoryRepository.findById(1).orElseThrow();
		RestaurantCategory pizzaCategory = restaurantCategoryRepository.findById(2).orElseThrow();
		RestaurantCategory japonesaCategory = restaurantCategoryRepository.findById(3).orElseThrow();
		
		Restaurant r = new Restaurant();
		r.setName("Mc Donalds's");
		r.setEmail("contact@mcdonalds.com");
		r.setPassword(StringUtils.encrypt("123"));
		r.setCnpj("12345678901234");
		r.setDeliveryTax(BigDecimal.valueOf(3.2));
		r.setPhonenumber("31988733237");
		r.getCategories().add(lanchesCategory);
        r.getCategories().add(pizzaCategory);
		r.setLogo("0001.restaurant.png");
		r.setDeliveryTime(10);
		r.setCep("31980540");
		restaurantRepository.save(r);
		restaurants.add(r);
		
		r = new Restaurant();
		r.setName("Burger King");
		r.setEmail("contact@burgerking.com");
		r.setPassword(StringUtils.encrypt("123"));
		r.setCnpj("12345678901235");
		r.setDeliveryTax(BigDecimal.valueOf(1.5));
		r.setPhonenumber("31988733238");
		r.getCategories().add(lanchesCategory);
		r.setLogo("0002.restaurant.png");
		r.setDeliveryTime(20);
		r.setCep("31980540");
		restaurantRepository.save(r);
		restaurants.add(r);
		
		r = new Restaurant();
		r.setName("Bob's");
		r.setEmail("contact@bobs.com");
		r.setPassword(StringUtils.encrypt("123"));
		r.setCnpj("12345678901236");
		r.setDeliveryTax(BigDecimal.valueOf(0));
		r.setPhonenumber("31988733239");
		r.getCategories().add(lanchesCategory);
		r.setLogo("0003.restaurant.png");
		r.setDeliveryTime(30);
		r.setCep("31980540");
		restaurantRepository.save(r);
		restaurants.add(r);
		
		r = new Restaurant();
		r.setName("Pizza Hut");
		r.setEmail("contact@pizzahut.com");
		r.setPassword(StringUtils.encrypt("123"));
		r.setCnpj("12345678901237");
		r.setDeliveryTax(BigDecimal.valueOf(2.5));
		r.setPhonenumber("31988733230");
		r.getCategories().add(pizzaCategory);
		r.setLogo("0004.restaurant.png");
		r.setDeliveryTime(40);
		r.setCep("31980540");
		restaurantRepository.save(r);
		restaurants.add(r);
		
		r = new Restaurant();
		r.setName("Temaki Now");
		r.setEmail("contact@temakinow.com");
		r.setPassword(StringUtils.encrypt("123"));
		r.setCnpj("12345678901231");
		r.setDeliveryTax(BigDecimal.valueOf(0));
		r.setPhonenumber("31988733231");
		r.getCategories().add(japonesaCategory);
		r.setLogo("0005.restaurant.png");
		r.setDeliveryTime(50);
		r.setCep("31980540");
		restaurantRepository.save(r);
		restaurants.add(r);
		
		Restaurant[] array = new Restaurant[restaurants.size()];
		return restaurants.toArray(array);
			
		
		}
	
	private Client[] clients() {
		List<Client> clients = new ArrayList<>();
		
		
		Client c = new Client();
		c.setName("Jhonny Silva");
		c.setEmail("contact@jhonny.com");
		c.setPassword(StringUtils.encrypt("123"));
		c.setCpf("11156477689");
		c.setPhonenumber("31988733237");
		c.setCep("31980540");
		clientRepository.save(c);
		clients.add(c);
		
		
		c = new Client();
		c.setName("Jhonny Lima");
		c.setEmail("j@j.com");
		c.setPassword(StringUtils.encrypt("123"));
		c.setCpf("11256477689");
		c.setPhonenumber("31988733227");
		c.setCep("31980640");
		clientRepository.save(c);
		clients.add(c);
		
		Client[] array = new Client[clients.size()];
		return clients.toArray(array);
	
	
		
		
	}
	
	private void itensMenu(Restaurant[] restaurants) {
		ItemMenu im = new ItemMenu();
		im.setCategory("Lanches");
		im.setDescription("Delicioso sanduíche de frango com legumes.");
		im.setName("Chiken Burger");
		im.setPrice(BigDecimal.valueOf(17.8));
		im.setRestaurant(restaurants[0]);
		im.setHighlight(true);
		im.setImage("0001-food.png");
		itemMenuRepository.save(im);
		
		
		im = new ItemMenu();
		im.setCategory("Pizzas");
		im.setDescription("Delicioso sanduíche de frango com legumes.");
		im.setName("Super Burger");
		im.setPrice(BigDecimal.valueOf(17.8));
		im.setRestaurant(restaurants[0]);
		im.setHighlight(false);
		im.setImage("0002-food.png");
		itemMenuRepository.save(im);
		
		
		
		
	}
	
	

}
