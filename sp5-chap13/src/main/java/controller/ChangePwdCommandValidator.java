package controller;

import org.springframework.validation.*;

public class ChangePwdCommandValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ChangePwdCommand.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword","required");
		ValidationUtils.rejectIfEmpty(errors, "newpassword", "required");
	}
}
