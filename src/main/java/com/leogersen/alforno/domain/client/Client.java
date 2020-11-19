package com.leogersen.alforno.domain.client;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.leogersen.alforno.domain.user.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class Client extends User{
	
	@NotBlank(message = "O campo CPF não pode estar vazio")
	@Pattern(regexp = "[0-9]{11}", message = "O CPF possui formato inválido")
	@Column(length = 11, nullable = false)
	private String cpf;
	
	@NotBlank(message = "O campo CEP não pode estar vazio")
	@Pattern(regexp = "[0-9]{8}", message = "O CEP possui formato inválido")
	@Column(length = 8)
	private String cep;
	

}
