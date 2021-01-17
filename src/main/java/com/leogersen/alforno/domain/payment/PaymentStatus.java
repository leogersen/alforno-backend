package com.leogersen.alforno.domain.payment;

public enum PaymentStatus {
	
	Authorized("Autorizado"),
	Unauthorized("Não Autorizado pela instituição financeira"),
	InvalidCard("Cartão Inválido");
	
	
	String description;
	
	PaymentStatus(String description) {
		this.description = description;
		
	}
	
	public String getDescription() {
		return description;
	}

}
