package com.huyraw.demo.util.annotations.validator;

import com.huyraw.demo.util.annotations.validatorImp.PasswordImplement;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordImplement.class)
public @interface Password {
    String message() default
            "Password should be contain 8 character or more, " +
            "include lower case, uppercase, " +
            "special character and number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
