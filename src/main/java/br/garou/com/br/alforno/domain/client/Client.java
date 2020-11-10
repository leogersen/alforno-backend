package br.garou.com.br.alforno.domain.client;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import br.garou.com.br.alforno.domain.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
@SuppressWarnings("serial")
public class Client extends User{
	
	@NotBlank(message = "O CPF não pode ser vazio")
	@Pattern(regexp = "[0-9] {10,11}", message = "O CPF possui formato inválido")
	@Column(length = 11)
	private String cpf;
	
	@NotBlank(message = "O CEP não pode ser vazio")
	@Pattern(regexp = "[0-9] {8}", message = "O CEP possui formato inválido")
	@Column(length = 8)
	private String cep;
	

}
