package br.com.rem_aya_2.exceptions;

public class RequiredObjectIsNullException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RequiredObjectIsNullException() {
		super("It is not allowed to persist a null object");
	}
	
	public RequiredObjectIsNullException(String exception) {
		super(exception);
	}
}
