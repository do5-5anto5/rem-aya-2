package br.com.rem_aya_2.exceptions;

public class MyFileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public MyFileNotFoundException(String exception) {
		super(exception);
	}
	
	public MyFileNotFoundException(String exeption, Throwable cause) {
		super(exeption, cause);
	}
}
