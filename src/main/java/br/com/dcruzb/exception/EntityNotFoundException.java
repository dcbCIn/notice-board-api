package br.com.dcruzb.exception;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1988794712486340609L;

	
	public EntityNotFoundException(String message) {
		super(message);
	}
}
