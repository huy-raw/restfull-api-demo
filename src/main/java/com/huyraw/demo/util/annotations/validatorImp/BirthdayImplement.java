package com.huyraw.demo.util.annotations.validatorImp;

import com.huyraw.demo.util.annotations.validator.Birthday;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BirthdayImplement implements ConstraintValidator<Birthday, String> {

    @Override
    public void initialize(Birthday constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            LocalDate dob = LocalDate.parse(value, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return dob.isBefore(LocalDate.now());
        }catch (Exception e) {
            return false;
        }
    }
}
