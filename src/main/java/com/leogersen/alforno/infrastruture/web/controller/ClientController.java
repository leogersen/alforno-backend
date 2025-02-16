package com.leogersen.alforno.infrastruture.web.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import com.leogersen.alforno.domain.order.*;
import com.leogersen.alforno.domain.restaurant.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.leogersen.alforno.application.service.ClientService;
import com.leogersen.alforno.application.service.RestaurantService;
import com.leogersen.alforno.application.service.ValidationException;
import com.leogersen.alforno.domain.client.Client;
import com.leogersen.alforno.domain.client.ClientRepository;
import com.leogersen.alforno.util.SecurityUtils;

@Controller
@RequestMapping(path = "/client")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private RestaurantCategoryRepository restaurantCategoryRepository;

	@Autowired
    private ItemMenuRepository itemMenuRepository;

	@Autowired
    private RestaurantRepository restaurantRepository;

	@Autowired
    private OrderRepository orderRepository;
	
	@Autowired 
	private ClientService clientService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@GetMapping(path = "/home")
	public String home(Model model) {
		
		List<RestaurantCategory> categories = restaurantCategoryRepository.findAll(Sort.by("name"));
		model.addAttribute("categories", categories);
		model.addAttribute("searchFilter", new SearchFilter());

		List<Order> orders =  orderRepository.listOfOrders(SecurityUtils.loggedClient().getId());
		model.addAttribute("orders", orders);


		
		
		
		return "client-home";
	}
	
	@GetMapping(path = "/edit")
	public String edit(Model model) {
		Integer clientId = SecurityUtils.loggedClient().getId();
		Client client = clientRepository.findById(clientId).orElseThrow(NoSuchElementException::new);
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
	
	@GetMapping(path = "/search")
	public String search(
			@ModelAttribute("searchFilter") SearchFilter filter,
			@RequestParam(value = "cmd", required = false) String command,
			Model model) {

		filter.processFilter(command);

		List<Restaurant> restaurants = restaurantService.search(filter); 
		model.addAttribute("restaurants", restaurants);
		
		ControllerHelper.addCategoriesToRequest(restaurantCategoryRepository, model);

		model.addAttribute("searchFilter", filter);
		
		return "client-search";
	}

	@GetMapping(path = "/restaurant")
    public String viewRestaurant(
            @RequestParam("restaurantId") Integer restaurantId,
            @RequestParam(value = "category", required = false) String category,
            Model model) {

	        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(NoSuchElementException::new);
	        model.addAttribute("restaurant", restaurant);
	        model.addAttribute("cep", SecurityUtils.loggedClient().getCep());

	        List<String> categories = itemMenuRepository.findCategories(restaurantId);
	        model.addAttribute("categories", categories);

	        List<ItemMenu> itemsMenuHighlight;
            List<ItemMenu> itemsMenuNotHighlight;

            if(category == null) {
                itemsMenuHighlight = itemMenuRepository.findByRestaurant_IdAndHighlightOrderByName(restaurantId, true);
                itemsMenuNotHighlight = itemMenuRepository.findByRestaurant_IdAndHighlightOrderByName(restaurantId, false);
            }else {
                itemsMenuHighlight = itemMenuRepository.findByRestaurant_IdAndHighlightAndCategoryOrderByName(restaurantId, true, category);
                itemsMenuNotHighlight = itemMenuRepository.findByRestaurant_IdAndHighlightAndCategoryOrderByName(restaurantId, false, category);
            }
        model.addAttribute("itemsMenuHighlight", itemsMenuHighlight);
        model.addAttribute("itemsMenuNotHighlight", itemsMenuNotHighlight);
        model.addAttribute("selectedCategory", category);

	    return "client-restaurant";
	}
}
