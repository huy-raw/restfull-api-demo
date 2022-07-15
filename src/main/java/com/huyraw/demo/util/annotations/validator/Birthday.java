package com.huyraw.demo.util.annotations.validator;


import com.huyraw.demo.util.annotations.validatorImp.BirthdayImplement;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BirthdayImplement.class)
public @interface Birthday {
    String message() default "Birth date is not valid!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
