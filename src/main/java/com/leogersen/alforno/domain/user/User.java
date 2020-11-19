package com.leogersen.alforno.domain.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.leogersen.alforno.util.StringUtils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuppressWarnings("serial")
@MappedSuperclass
public class User implements Serializable {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "O campo nome n�o pode estar vazio")
	@Size(max = 80, message = "O nome � muito grande")
	private String name;
	
	@NotBlank(message = "O campo e-mail n�o pode estar vazio")
	@Size(max = 60, message = "O e-mail � muito grande")
	@Email(message = "O e-mail � inv�lido")
	private String email;
	
	@NotBlank(message = "O campo senha n�o pode estar vazio")
	@Size(max = 80, message = "A senha � muito grande")
	private String password;
	
	@NotBlank(message = "O campo campo telefone n�o pode estar vazio")
	@Pattern(regexp = "[0-9]{10,14}", message = "O telefone possui formato inv�lido")
	@Column(length = 11, nullable = false)
	private String phonenumber;
	
	public void encryptPassword() {
		this.password = StringUtils.encrypt(this.password);
	}

}
