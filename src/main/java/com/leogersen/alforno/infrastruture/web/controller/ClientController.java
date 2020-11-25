package com.leogersen.alforno.infrastruture.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leogersen.alforno.application.service.ClientService;
import com.leogersen.alforno.application.service.ValidationException;
import com.leogersen.alforno.domain.client.Client;
import com.leogersen.alforno.domain.client.ClientRepository;
import com.leogersen.alforno.domain.restaurant.RestaurantCategory;
import com.leogersen.alforno.domain.restaurant.RestaurantCategoryRepository;
import com.leogersen.alforno.util.SecurityUtils;

@Controller
@RequestMapping(path = "/client")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private RestaurantCategoryRepository restaurantCategoryRepository;
	
	@Autowired 
	private ClientService clientService;
	
	@GetMapping(path = "/home")
	public String home(Model model) {
		
		List<RestaurantCategory> categories = restaurantCategoryRepository.findAll(Sort.by("name"));
		model.addAttribute("categories", categories);
		
		
		
		return "client-home";
	}
	
	@GetMapping(path = "/edit")
	public String edit(Model model) {
		Integer clientId = SecurityUtils.loggedClient().getId();
		Client client = clientRepository.findById(clientId).orElseThrow();
		model.addAttribute("client", client);
		ControllerHelper.setEditMode(model, true);
		
		return "client-signup";
		
	
	}
	
	@PostMapping(path = "/save")
	public String save(@ModelAttribute("client") @Valid Client client, 
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
}
