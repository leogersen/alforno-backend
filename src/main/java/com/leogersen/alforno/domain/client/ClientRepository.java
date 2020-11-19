package com.leogersen.alforno.domain.client;

import org.springframework.data.jpa.repository.JpaRepository;

	public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	public Client findByEmail(String email);

}
