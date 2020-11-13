package br.garou.com.br.alforno.domain.restaurant;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import br.garou.com.br.alforno.domain.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
@Table(name = "restaurant")
public class Restaurant extends User{
	
	
	@Size(max = 80)
	private String logo;
	
	private transient MultipartFile logoFile;
	
	@NotBlank(message = "O campo CNPJ não pode estar vazio")
	@Pattern(regexp = "[0-9]{14}", message = "O CNPJ possui formato inválido")
	@Column(length = 14, nullable = false)
	private String cnpj;
	
	@NotBlank(message = "O campo CEP não pode estar vazio")
	@Pattern(regexp = "[0-9]{8}", message = "O CEP possui formato inválido")
	@Column(length = 8)
	private String cep;
	
	@NotNull(message = "O campo taxa de entrega não pode estar vazio")
	@Min(0)
	@Max(120)
	private BigDecimal deliveryTax;
	
	@NotNull(message = "O campo tempo de entrega não pode estar vazio")
	@Min(0)
	@Max(120)
	private Integer deliveryTime;
	
	@ManyToMany
	@JoinTable(
		name = "restaurant_has_category",
		joinColumns = @JoinColumn(name = "restaurant_id"),
		inverseJoinColumns = @JoinColumn(name = "restaurant_category_id")
		
		)

	private Set<RestaurantCategory> categories = new HashSet<>(0);
	
	public void setLogoFileName() {
		if (getId() == null) {
			throw new IllegalStateException("É preciso primeiro gravar o registro");
			
		}
		//TODO change file format
		this.logo = String.format("af_restaurant_%04d.%s", getId(), ".png");
	}

}