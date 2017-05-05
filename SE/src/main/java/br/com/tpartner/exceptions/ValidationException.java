package br.com.tpartner.exceptions;

import org.springframework.validation.BindingResult;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = -2935416880875073025L;

	private BindingResult validationResult;

	public ValidationException(BindingResult resultadoValidacao) {
		this.validationResult = resultadoValidacao;
	}

	public BindingResult getValidationResult() {
		return validationResult;
	}
}
