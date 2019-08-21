package de.pharos.myPosts.security;

import de.pharos.myPosts.dao.UserDao;
import de.pharos.myPosts.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class MyDaoAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private UserDao userDao;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        Optional<User> optional = userDao.findByEmail(auth.getName());
        if (optional.isPresent()) {
            final Authentication result = super.authenticate(auth);
            return new UsernamePasswordAuthenticationToken(optional.get(), result.getCredentials(), result.getAuthorities());
        }
        throw new BadCredentialsException("Invalid username or password");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}