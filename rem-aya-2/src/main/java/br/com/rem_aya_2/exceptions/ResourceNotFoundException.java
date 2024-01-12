package br.com.rem_aya_2.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String exception) {
		super(exception);
	}
	
}
