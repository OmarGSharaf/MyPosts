package de.pharos.myPosts.validation;

import de.pharos.myPosts.annotation.PasswordMatches;
import de.pharos.myPosts.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        return matchPasswords((UserDto) obj);
    }

    /**
     * Validates that the password and confirm password matching, returns true if they match, false otherwise.
     *
     * @param userDto userDto to be validated.
     * @return true if passwords match, false otherwise.
     */
    private boolean matchPasswords(UserDto userDto) {
        return userDto.getPassword().equals(userDto.getConfirmPassword());
    }
}