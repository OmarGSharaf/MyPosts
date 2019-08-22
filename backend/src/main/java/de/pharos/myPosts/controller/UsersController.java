package de.pharos.myPosts.controller;

import de.pharos.myPosts.dto.LoginDto;
import de.pharos.myPosts.dto.UserDto;
import de.pharos.myPosts.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = "Users", description = "Set of endpoints for authenticating and registering users.")
@RequestMapping("/v1/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    @ApiOperation("Authenticate users and generate Tokens")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody LoginDto loginDto)
            throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        return ResponseEntity.ok(userService.generateToken(loginDto.getEmail()));
    }

    @PostMapping("/register")
    @ApiOperation("Register a new user to the system")
    public ResponseEntity register(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.save(userDto));
    }
}