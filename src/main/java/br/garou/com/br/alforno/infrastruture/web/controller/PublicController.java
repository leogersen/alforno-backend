package br.garou.com.br.alforno.infrastruture.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.garou.com.br.alforno.application.ClientService;
import br.garou.com.br.alforno.application.RestaurantService;
import br.garou.com.br.alforno.application.ValidationException;
import br.garou.com.br.alforno.domain.client.Client;
import br.garou.com.br.alforno.domain.restaurant.Restaurant;


@Controller
@RequestMapping(path = "/public")
public class PublicController {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping("/client/new")
	public String newClient(Model model) {
		model.addAttribute("client", new Client());
		ControllerHelper.setEditMode(model, false);
		return "client-signup";
	}
	
	@PostMapping(path = "/client/save")
	public String saveClient(
			@ModelAttribute("client") @Valid Client client, 
			Errors errors, 
			Model model) {
		
		if (!errors.hasErrors()) {
			try {
			clientService.saveClient(client);
			model.addAttribute("msg", "Cliente gravado com Sucesso!");
			
			} catch (ValidationException e) {
				errors.rejectValue("email", null, e.getMessage());
				
			}
			
			
		}
		ControllerHelper.setEditMode(model, false);
		return "client-signup";
		}
	
	
	@Autowired
	private RestaurantService restaurantService;
	
	
	@GetMapping("/restaurant/new")
	public String newRestaurant(Model model) {
		model.addAttribute("restaurant", new Restaurant());
		ControllerHelper.setEditMode(model, false);
		return "restaurant-signup";
	}

	@PostMapping(path = "/restaurant/save")
	public String saveRestaurant(
			@ModelAttribute("restaurant") @Valid Restaurant restaurant, 
			Errors errors, 
			Model model) {
		
		if (!errors.hasErrors()) {
			try {
			restaurantService.saveRestaurant(restaurant);
			model.addAttribute("msg", "Restaurante gravado com Sucesso!");
			
			} catch (ValidationException e) {
				errors.rejectValue("email", null, e.getMessage());	
			}		
		}
		ControllerHelper.setEditMode(model, false);
		return "restaurant-signup";
		}
			
}
