package be.commit_wealth.validation;

import be.commit_wealth.constants.ConstantValue;
import be.commit_wealth.dto.UserRegistrationRequest;
import be.commit_wealth.utils.DateConverter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RegistrationValidator implements ConstraintValidator<RegistrationValidation, UserRegistrationRequest> {
    boolean isValid = true;

    @Override
    public boolean isValid(UserRegistrationRequest value, ConstraintValidatorContext context) {

        if (value.getUsername().isEmpty() || !value.getUsername().matches(ConstantValue.USERNAME_PATTERN)) {
            context.buildConstraintViolationWithTemplate(ConstantValue.USERNAME_CONSTRAINT_ERROR_MESSAGE)
                    .addPropertyNode("username") // <--- หัวใจสำคัญอยู่ตรงนี้!
                    .addConstraintViolation();
            isValid = false;
        }

        if(value.getPassword().length() < ConstantValue.PASSWORD_MIN_LENGTH) {
            context.buildConstraintViolationWithTemplate(ConstantValue.PASSWORD_BELOW_MINIMUM_ERROR_MESSAGE)
                    .addPropertyNode("password") // <--- หัวใจสำคัญอยู่ตรงนี้!
                    .addConstraintViolation();
            isValid = false;
        }

        boolean stringValidation = value.getUsername().matches(ConstantValue.PASSWORD_REGEX);
        if(value.getPassword() == null || !value.getPassword().matches(ConstantValue.PASSWORD_REGEX)) {
            context.buildConstraintViolationWithTemplate(ConstantValue.PASSWORD_CONSTRAIN_ERROR_MESSAGE)
                    .addPropertyNode("password") // <--- หัวใจสำคัญอยู่ตรงนี้!
                    .addConstraintViolation();
            isValid = false;
        }

        if(value.getEmail() == null || value.getEmail().matches(ConstantValue.EMAIL_PATTERN)) {
            context.buildConstraintViolationWithTemplate(ConstantValue.EMAIL_CONSTRAIN_ERROR_MESSAGE)
                    .addPropertyNode("email") // <--- หัวใจสำคัญอยู่ตรงนี้!
                    .addConstraintViolation();
            isValid = false;
        }


        if(value.getBirthdayDate() == null) {
            try {
                DateConverter.convertStringToDateTime(value.getBirthdayDate(), ConstantValue.DATE_TIME_PATTERN);
            } catch (RuntimeException e) {
                context.buildConstraintViolationWithTemplate(ConstantValue.DATE_TIME_CONSTRAIN_ERROR_MESSAGE)
                        .addPropertyNode("birthdayDate")
                        .addConstraintViolation();
                isValid = false;
            }
        }

        return isValid;
    }
}
