package de.pharos.myPosts.validation;

import de.pharos.myPosts.annotation.ValidEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public void initialize(final ValidEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(final String email, final ConstraintValidatorContext context) {
        return validateEmail(email);
    }

    /**
     * The Email address is validated using the java.util.regex.Pattern.matches() method. It matches the regular
     * expression for the E-mail and the given input Email and returns true if they match and false otherwise.
     *
     * @param email user email.
     * @return true if email matches pattern, false otherwise.
     */
    private boolean validateEmail(final String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
