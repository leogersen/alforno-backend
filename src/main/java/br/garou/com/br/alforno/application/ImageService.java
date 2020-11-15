package br.garou.com.br.alforno.application;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.garou.com.br.alforno.util.IOUltils;

@Service
public class ImageService {
	
	@Value("${alforno.files.logo}")
	private String logoDir;
	
	public void uploadLogo(MultipartFile multiPartFile, String fileName) {
		try {
			IOUltils.copy(multiPartFile.getInputStream(), fileName, logoDir);
		} catch (IOException e) {
			throw new ApplicationServiceException(e);
		}
		
	}

}
