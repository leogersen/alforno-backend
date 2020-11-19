package com.leogersen.alforno.domain.restaurant;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.leogersen.alforno.infrastruture.web.validator.UploadConstraint;
import com.leogersen.alforno.util.FileType;

import lombok.Getter;
import lombok.Setter;

@Entity
@SuppressWarnings("serial")
@Table(name = "item_menu")
@Getter
@Setter
@lombok.EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemMenu implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "O campo nome não pode estar vazio")
	@Size(max = 50)
	private String name;
	
	@NotBlank(message = "O campo categoria não pode estar vazio")
	@Size(max = 25)
	private String category;
	
	@NotBlank(message = "O campo descrição não pode estar vazio")
	@Size(max = 80)
	private String description;
	
	@Size(max = 50)
	private String image;	
	
	@NotNull(message = "O campo preço não pode estar vazio")
	@Min(0)
	private BigDecimal price;
	
	@NotNull
	private boolean highlight;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;
	
	@UploadConstraint(acceptedTypes = FileType.PNG, message = "O arquivo não é válido" )
	private transient MultipartFile imageFile;
	
	public void SetImageFileName() {
		if (getId() == null) {
			throw new IllegalStateException("O objeto precisa primeiro ser criado");
		}
		
		this.image = String.format("%04d-food.%s", getId(), FileType.of(imageFile.getContentType()).getExtension());
	}
	
	

}
