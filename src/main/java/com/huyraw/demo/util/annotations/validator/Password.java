package com.huyraw.demo.util.annotations.validator;

import com.huyraw.demo.util.annotations.validatorImp.PasswordImplement;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = PasswordImplement.class)
public @interface Password {
    String message() default
            "Password should be contain 8 character or more, " +
            "include lower case, uppercase, " +
            "special character and number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
