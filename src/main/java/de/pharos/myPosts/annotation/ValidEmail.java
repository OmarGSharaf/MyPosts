package de.pharos.myPosts.annotation;

import de.pharos.myPosts.validation.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface ValidEmail {

    String message() default "Invalid Email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}