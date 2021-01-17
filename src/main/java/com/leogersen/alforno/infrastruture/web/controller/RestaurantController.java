package com.leogersen.alforno.infrastruture.web.controller;


import com.leogersen.alforno.application.service.*;
import com.leogersen.alforno.application.service.ValidationException;
import com.leogersen.alforno.domain.client.*;
import com.leogersen.alforno.domain.restaurant.*;
import com.leogersen.alforno.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@Controller
@RequestMapping(path = "/restaurant")
public class RestaurantController {


    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantCategoryRepository restaurantCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(path = "/home")
    public String home() {
        return "restaurant-home";

    }

    @GetMapping(path = "/edit")
    public String edit(Model model) {
            Integer restaurantId = SecurityUtils.loggedRestaurant().getId();
            Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow();
            model.addAttribute("restaurant", restaurant);
            ControllerHelper.setEditMode(model, true);
            ControllerHelper.addCategoriesToRequest(restaurantCategoryRepository, model);

            return "restaurant-signup";
    }

    @PostMapping(path = "/save")
    public String save(@ModelAttribute("restaurant") @Valid Restaurant restaurant,
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
