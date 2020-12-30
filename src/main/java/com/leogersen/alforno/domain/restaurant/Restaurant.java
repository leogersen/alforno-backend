package com.leogersen.alforno.domain.restaurant;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.leogersen.alforno.util.*;
import org.springframework.web.multipart.MultipartFile;

import com.leogersen.alforno.domain.user.User;
import com.leogersen.alforno.infrastruture.web.validator.UploadConstraint;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
@Table(name = "restaurant")
public class Restaurant extends User{
	
	
	@Size(max = 80)
	private String logo;
	
	@UploadConstraint(acceptedTypes = FileType.PNG, message = "O arquivo � inv�lido")
	private transient MultipartFile logoFile;
	
	@NotBlank(message = "O campo CNPJ n�o pode estar vazio")
	@Pattern(regexp = "[0-9]{14}", message = "O CNPJ possui formato inv�lido")
	@Column(length = 14, nullable = false)
	private String cnpj;
	
	@NotBlank(message = "O campo CEP n�o pode estar vazio")
	@Pattern(regexp = "[0-9]{8}", message = "O CEP possui formato inv�lido")
	@Column(length = 8)
	private String cep;
	
	@NotNull(message = "O campo taxa de entrega n�o pode estar vazio")
	@Min(0)
	@Max(120)
	private BigDecimal deliveryTax;
	
	@NotNull(message = "O campo tempo de entrega n�o pode estar vazio")
	@Min(0)
	@Max(120)
	private Integer deliveryTime;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "restaurant_has_category",
		joinColumns = @JoinColumn(name = "restaurant_id"),
		inverseJoinColumns = @JoinColumn(name = "restaurant_category_id")
		
		)
	
	@ToString.Exclude
	private Set<RestaurantCategory> categories = new HashSet<>(0);
	
	@OneToMany(mappedBy = "restaurant")
	private Set<ItemMenu> itensMenu = new HashSet<>(0);
	
	public void setLogoFileName() {
		if (getId() == null) {
			throw new IllegalStateException("� preciso primeiro gravar o registro");
			
		}
		
		this.logo = String.format("af_restaurant_%04d.%s", getId(), FileType.of(logoFile.getContentType()).getExtension());
	}
	
	public String getCategoriesAsText() {
		Set<String> strings = new LinkedHashSet<>();
		
		for(RestaurantCategory category : categories) {
			strings.add(category.getName());
			
			
		}
		return StringUtils.concatenate(strings);
		
	}

	public Integer deliveryTimeCalculator(String cep) {

	    if (cep.equals(SecurityUtils.loggedClient().getCep())){
	        return deliveryTime;
        } else {
	        throw new IllegalArgumentException("Infelizmente o restaurante n�o entrega para o seu CEP");
        }



    }

}