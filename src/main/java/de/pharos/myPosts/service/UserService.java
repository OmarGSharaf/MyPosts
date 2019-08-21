package de.pharos.myPosts.service;

import de.pharos.myPosts.dao.UserDao;
import de.pharos.myPosts.dto.RegistrationDto;
import de.pharos.myPosts.dto.TokenDto;
import de.pharos.myPosts.dto.UserDto;
import de.pharos.myPosts.exception.UserException;
import de.pharos.myPosts.model.User;
import de.pharos.myPosts.utility.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper = new ModelMapper();

    /**
     * Finds a User by JSON Web Token (JWT).
     *
     * @param token user token.
     * @return the user.
     */
    public User findByToken(String token) throws UserException {
        String email = jwtUtil.getUsernameFromToken(token.substring(7));
        Optional<User> user = userDao.findByEmail(email);
        if (user.isPresent()) return user.get();
        throw new UserException("USER_NOT_FOUND");
    }

    /**
     * Generate a new JWT for a user given his email.
     *
     * @param email user email.
     * @return the Token transfer object.
     */
    public TokenDto generateToken(String email){
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        String token = jwtUtil.generateToken(userDetails);
        return new TokenDto(token);
    }

    /**
     * Saves a User. User DTO is mapped to User object. The password is encoded before saving.
     *
     * @param userDto the user details without the user.
     * @return registration dto.
     */
    public RegistrationDto save(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userDao.save(user);

        RegistrationDto registrationDto = modelMapper.map(user, RegistrationDto.class);
        registrationDto.setToken(generateToken(user.getEmail()).getToken());
        return registrationDto;
    }
}