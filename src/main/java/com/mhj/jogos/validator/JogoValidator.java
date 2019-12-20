package com.mhj.jogos.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mhj.jogos.model.Jogo;

public class JogoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Jogo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Jogo jogo = (Jogo) target;
		if (jogo.getDezenasList().size() < 15) {
//			ValidationUtils.rejectIfEmpty(errors, "validator", "Minimo 15 dezenas");
			errors.rejectValue("dezenas", "lotofacil.minimo.dezenas");
		}
	}

}
