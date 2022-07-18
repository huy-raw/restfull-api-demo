package com.huyraw.demo.util.annotations.validator;


import com.huyraw.demo.util.annotations.validatorImp.BirthdayImplement;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = BirthdayImplement.class)
public @interface Birthday {
    String message() default "Birth date is not valid!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
