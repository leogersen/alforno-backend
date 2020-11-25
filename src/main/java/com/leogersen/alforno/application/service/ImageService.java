package com.leogersen.alforno.application.service;

import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.leogersen.alforno.util.IOUltils;

@Service
public class ImageService {
	
	@Value("${alforno.files.logo}")
	private String logoDir;
	
	@Value("${alforno.files.category}")
	private String categoryDir;
	
	@Value("${alforno.files.food}")
	private String foodDir;
	
	
	
	public void uploadLogo(MultipartFile multiPartFile, String fileName) {
		try {
			IOUltils.copy(multiPartFile.getInputStream(), fileName, logoDir);
		} catch (IOException e) {
			throw new ApplicationServiceException(e);
		}
		
	}

	
	
		public byte[] getBytes(String type, String imgName) {
			
			String dir;
			
			
			try {
				if("food".equals(type)) {
					dir = foodDir;
				}
				
				else if("logo".equals(type)) {
					dir = logoDir;
				}
				
				else if("category".equals(type)) {
					dir = categoryDir;
				}
				else {
					throw new Exception(type + "Não é um tipo de imagem válida");
				}
				
				return IOUltils.getBytes(Paths.get(dir, imgName));
				
			} catch (Exception e) {
				throw new ApplicationServiceException(e);
			}
		}
		
	}
