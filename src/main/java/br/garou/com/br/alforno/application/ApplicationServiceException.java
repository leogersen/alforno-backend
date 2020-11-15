package br.garou.com.br.alforno.application;

@SuppressWarnings("serial")
public class ApplicationServiceException extends RuntimeException {

	public ApplicationServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationServiceException(String message) {
		super(message);
	}

	public ApplicationServiceException(Throwable cause) {
		super(cause);
	}
	
}
