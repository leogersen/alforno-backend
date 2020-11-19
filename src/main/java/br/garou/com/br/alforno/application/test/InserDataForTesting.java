package br.garou.com.br.alforno.application.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.garou.com.br.alforno.domain.client.Client;
import br.garou.com.br.alforno.domain.client.ClientRepository;
import br.garou.com.br.alforno.domain.restaurant.Restaurant;
import br.garou.com.br.alforno.domain.restaurant.RestaurantCategory;
import br.garou.com.br.alforno.domain.restaurant.RestaurantCategoryRepository;
import br.garou.com.br.alforno.domain.restaurant.RestaurantRepository;
import br.garou.com.br.alforno.util.StringUtils;

@Component
public class InserDataForTesting {
	
	
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private RestaurantCategoryRepository restaurantCategoryRepository;
	
	
	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		clients();
		@SuppressWarnings("unused")
		Restaurant[] restaurants = restaurants();
		
		
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
		r.setLogo("0001.restaurant.png");
		r.setDeliveryTime(30);
		r.setCep("31980540");
		restaurantRepository.save(r);
		restaurants.add(r);
		
		r = new Restaurant();
		r.setName("Burger King");
		r.setEmail("contact@burgerking.com");
		r.setPassword(StringUtils.encrypt("123"));
		r.setCnpj("12345678901235");
		r.setDeliveryTax(BigDecimal.valueOf(3.2));
		r.setPhonenumber("31988733238");
		r.getCategories().add(lanchesCategory);
		r.setLogo("0002.restaurant.png");
		r.setDeliveryTime(30);
		r.setCep("31980540");
		restaurantRepository.save(r);
		restaurants.add(r);
		
		r = new Restaurant();
		r.setName("Bob's");
		r.setEmail("contact@bobs.com");
		r.setPassword(StringUtils.encrypt("123"));
		r.setCnpj("12345678901236");
		r.setDeliveryTax(BigDecimal.valueOf(3.2));
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
		r.setDeliveryTax(BigDecimal.valueOf(3.2));
		r.setPhonenumber("31988733230");
		r.getCategories().add(pizzaCategory);
		r.setLogo("0004.restaurant.png");
		r.setDeliveryTime(30);
		r.setCep("31980540");
		restaurantRepository.save(r);
		restaurants.add(r);
		
		r = new Restaurant();
		r.setName("Temaki Now");
		r.setEmail("contact@temakinow.com");
		r.setPassword(StringUtils.encrypt("123"));
		r.setCnpj("12345678901231");
		r.setDeliveryTax(BigDecimal.valueOf(3.2));
		r.setPhonenumber("31988733231");
		r.getCategories().add(japonesaCategory);
		r.setLogo("0005.restaurant.png");
		r.setDeliveryTime(30);
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

}
