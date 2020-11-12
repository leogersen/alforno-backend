package br.garou.com.br.alforno.domain.client;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import br.garou.com.br.alforno.domain.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class Client extends User{
	
	@NotBlank(message = "O campo CPF n�o pode estar vazio")
	@Pattern(regexp = "[0-9]{11}", message = "O CPF possui formato inv�lido")
	@Column(length = 11, nullable = false)
	private String cpf;
	
	@NotBlank(message = "O campo CEP n�o pode estar vazio")
	@Pattern(regexp = "[0-9]{8}", message = "O CEP possui formato inv�lido")
	@Column(length = 8)
	private String cep;
	

}
