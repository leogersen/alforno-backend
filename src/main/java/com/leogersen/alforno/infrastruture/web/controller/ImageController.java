package com.leogersen.alforno.infrastruture.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leogersen.alforno.application.service.ImageService;

@Controller
public class ImageController {
	
	@Autowired
	private ImageService imageService;
	
	@GetMapping(path = "/image/{type}/{imgName}", produces = MediaType.IMAGE_PNG_VALUE)
	@ResponseBody
	public byte[] getBytes(
		@PathVariable("type") String type,
		@PathVariable("imgName") String imgName) {
			
		return imageService.getBytes(type, imgName);
		
	}

}
