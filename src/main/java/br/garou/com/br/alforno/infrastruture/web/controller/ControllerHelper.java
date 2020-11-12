package br.garou.com.br.alforno.infrastruture.web.controller;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

import br.garou.com.br.alforno.domain.restaurant.RestaurantCategory;
import br.garou.com.br.alforno.domain.restaurant.RestaurantCategoryRepository;

public class ControllerHelper {
	
	public static void setEditMode(Model model, boolean isEdit) {
		
		model.addAttribute("editMode", isEdit);
		
	}

	public static void addCategoriesToRequest(RestaurantCategoryRepository repository, Model model) {
		
		List<RestaurantCategory> categories = repository.findAll(Sort.by("name"));
		model.addAttribute("categories", categories);
		
		
	}

}
