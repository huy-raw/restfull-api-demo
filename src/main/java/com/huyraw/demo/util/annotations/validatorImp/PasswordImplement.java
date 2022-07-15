package com.huyraw.demo.util.annotations.validatorImp;

import com.huyraw.demo.util.annotations.validator.Password;
import org.jetbrains.annotations.TestOnly;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordImplement implements ConstraintValidator<Password, String> {
    final Integer MIN_VALUES = 8;
    final Integer MAX_VALUES = 20;
    final String PATTERN =  "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,}";

    final Pattern pattern = Pattern.compile(PATTERN);

    @Override
    public void initialize(Password constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        try {
            if (password != null &&
                    password.length() >= MIN_VALUES &&
                    password.length() <= MAX_VALUES) {
                    return pattern.matcher(password).matches();
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }


}
