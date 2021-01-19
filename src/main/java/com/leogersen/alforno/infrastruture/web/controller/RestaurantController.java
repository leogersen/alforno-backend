package com.leogersen.alforno.infrastruture.web.controller;


import com.leogersen.alforno.application.service.*;
import com.leogersen.alforno.application.service.ValidationException;
import com.leogersen.alforno.domain.order.*;
import com.leogersen.alforno.domain.restaurant.*;
import com.leogersen.alforno.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@Controller
@RequestMapping(path = "/restaurant")
public class RestaurantController {


    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantCategoryRepository restaurantCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ItemMenuRepository itemMenuRepository;

    @Autowired
    private ItemMenuService itemMenuService;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping(path = "/home")
    public String home(Model model) {
        Integer restaurantId = SecurityUtils.loggedRestaurant().getId();
        List<Order> orders = orderRepository.findByRestaurant_IdOrderByDataDesc(restaurantId);
        model.addAttribute("orders", orders);


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

        ControllerHelper.setEditMode(model, true);
        ControllerHelper.addCategoriesToRequest(restaurantCategoryRepository, model);
        return "restaurant-signup";
    }


    @GetMapping(path = "/foods")
    private String foods(Model model) {

            Integer restaurantId = SecurityUtils.loggedRestaurant().getId();
            Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow();
            model.addAttribute("restaurant", restaurant);

            List<ItemMenu> itemsMenu;
            itemsMenu = itemMenuRepository.findByRestaurant_IdOrderByName(restaurantId);

            model.addAttribute("itemsMenu", itemsMenu);
            model.addAttribute("itemMenu", new ItemMenu());

        return "restaurant-foods";
    }

    @GetMapping(path = "/foods/remove")
    public String remove(@RequestParam("itemId") Integer itemId,
            Model model){
        itemMenuRepository.deleteById(itemId);
        return "redirect:/restaurant/foods";
    }


    @PostMapping(path = "/foods/save")
    public String saveItem(
            @Valid @ModelAttribute("itemMenu") ItemMenu itemMenu,
            Errors errors,
            Model model) {

        if (errors.hasErrors()) {
            Integer restaurantId = SecurityUtils.loggedRestaurant().getId();
            Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow();
            model.addAttribute("restaurant", restaurant);

            List<ItemMenu> itemsMenu;
            itemsMenu = itemMenuRepository.findByRestaurant_IdOrderByName(restaurantId);

            model.addAttribute("itemsMenu", itemsMenu);
            model.addAttribute("msg", errors.toString());
            return "restaurant-foods";

            }
        itemMenuService.saveItemMenu(itemMenu);
        model.addAttribute("msg", "Item salvo com sucesso");
        return "redirect:/restaurant/foods";

    }

    @GetMapping(path = "/order")
    public String viewOrder(
            @RequestParam("orderId") Integer orderId,
            Model model){
        Order order = orderRepository.findById(orderId).orElseThrow();
        model.addAttribute("order", order);



        return "restaurant-order";

    }

    @PostMapping(path = "/order/nextStatus")
    public String nextStatus(
            @RequestParam("orderId") Integer orderId,
            Model model){

        Order order = orderRepository.findById(orderId).orElseThrow();
        order.defineNextStatus();
        orderRepository.save(order);

        model.addAttribute("order", order);
        model.addAttribute("msg", "Status alterado com sucesso");

        return "restaurant-order";

    }

    @GetMapping(path = "/report/orders")
    public String orderReport(
            @ModelAttribute("reportOrderFilter") ReportOrderFilter filter,
            Model model){

        Integer restaurantId = SecurityUtils.loggedRestaurant().getId();
        List<Order> orders = orderRepository.findByRestaurant_IdOrderByDataDesc(restaurantId);

        model.addAttribute("orders", orders);
        model.addAttribute("filter", filter);



        return "restaurant-orders-report";

    }
}
