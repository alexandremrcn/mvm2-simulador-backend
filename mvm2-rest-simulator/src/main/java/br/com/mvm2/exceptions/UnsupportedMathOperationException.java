package br.com.mvm2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedMathOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnsupportedMathOperationException(String message) {
		super(message != null && !message.trim().isEmpty() ? message : "Parâmetros inválidos para a operação solicitada.");
	}
}

