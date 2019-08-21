package de.pharos.myPosts.security;

import de.pharos.myPosts.dao.UserDao;
import de.pharos.myPosts.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service("userDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    public MyUserDetailsService() {
        super();
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        try {
            Optional<User> optional = userDao.findByEmail(email);

            if (!optional.isPresent()) {
                throw new UsernameNotFoundException("No user found with email: " + email);
            } else {
                User user = optional.get();
                return new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        new ArrayList<>());
            }
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}