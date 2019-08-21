package de.pharos.myPosts.validation;

import de.pharos.myPosts.annotation.ValidStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class StatusValidator implements ConstraintValidator<ValidStatus, String> {

    @Override
    public void initialize(final ValidStatus constraintAnnotation) {
    }

    @Override
    public boolean isValid(final String status, final ConstraintValidatorContext context) {
        return validateStatus(status);
    }

    /**
     * Validates that a post status is either private or public.
     *
     * @param status post status.
     * @return true if status is either PUBLIC or PRIVATE, false otherwise.
     */
    private boolean validateStatus(final String status) {
        return Arrays.asList("PRIVATE", "PUBLIC").contains(status);
    }
}