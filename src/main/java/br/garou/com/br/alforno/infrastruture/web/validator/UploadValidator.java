package br.garou.com.br.alforno.infrastruture.web.validator;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import br.garou.com.br.alforno.util.FileType;

public class UploadValidator implements ConstraintValidator<UploadConstraint, MultipartFile> {
	
	
	private List<FileType> acceptedFileTypes;
	
	
	@Override
	public void initialize(UploadConstraint constraintAnnotation) {
		acceptedFileTypes = Arrays.asList(constraintAnnotation.acceptedTypes());
	}

	@Override
	public boolean isValid(MultipartFile multipartfile, ConstraintValidatorContext context) {
		if (multipartfile == null) {
			return true;
		}
		for (FileType fileType : acceptedFileTypes) {
		if (fileType.sameOf(multipartfile.getContentType())) {
			return true;
			}
		}
		
		return false;

		
	}
	
	

}
