package com.shgame.APIGameWallet.virtualwallet.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidFieldException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public InvalidFieldException(String message) {
		this.message = message;
	}
	

}
