package br.garou.com.br.alforno.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.garou.com.br.alforno.domain.client.Client;
import br.garou.com.br.alforno.domain.client.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public void saveClient(Client client) throws ValidationException {
		
		if (!validateEmail(client.getEmail(), client.getId())) {
			throw new ValidationException("O e-mail já está cadastrado");
		
		}
		
		if (client.getId() != null) {
			Client clientDB = clientRepository.findById(client.getId()).orElseThrow();
			client.setPassword(clientDB.getPassword());
			
		}else {
			client.encryptPassword();
		}
		
		
		clientRepository.save(client);
	}
	
	private boolean validateEmail(String email, Integer id)  {
		Client client = clientRepository.findByEmail(email);
		
		if (client != null) { 
			if (id == null){
		     return false;	
		     
			}
			
			if(!client.getId().equals(id)) {
				return false;
			} 
				
		}
			
			return true;
		
}
}