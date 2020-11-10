package br.garou.com.br.alforno.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.garou.com.br.alforno.domain.client.Client;
import br.garou.com.br.alforno.domain.client.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public void saveClient(Client client) {
		clientRepository.save(client);
		
	}

}
