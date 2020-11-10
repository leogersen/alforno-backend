package br.garou.com.br.alforno.application;

@SuppressWarnings("serial")
public class ValidationException extends Exception {
	
	public ValidationException (String message) {
		super(message);
	}

}
