package de.pharos.myPosts.validation;

import de.pharos.myPosts.annotation.NewEmail;
import de.pharos.myPosts.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Transactional
public class NewEmailValidator implements ConstraintValidator<NewEmail, String> {

    @Autowired
    private UserDao userDao;

    @Override
    public void initialize(final NewEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(final String email, final ConstraintValidatorContext context) {
        return !exists(email);
    }

    /**
     * Validate an email by checking if an email is already registered for a user or not.
     *
     * @param email user email.
     * @return true if email already exists, false otherwise.
     */
    private boolean exists(String email) {
        return userDao.existsByEmail(email);
    }
}