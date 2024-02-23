package br.com.rem_aya_2.exceptions;

public class FileStorageException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public FileStorageException(String exception) {
		super(exception);
	}
	
	public FileStorageException(String exeption, Throwable cause) {
		super(exeption, cause);
	}
}
