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
import br.garou.com.br.alforno.domain.client.Client;


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
			clientService.saveClient(client);
			model.addAttribute("msg", "Cliente gravado com Sucesso!");
			
		}
		ControllerHelper.setEditMode(model, false);
		return "client-signup";
		}
}
