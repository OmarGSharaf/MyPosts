package de.pharos.myPosts.dto;

import de.pharos.myPosts.annotation.NewEmail;
import de.pharos.myPosts.annotation.PasswordMatches;
import de.pharos.myPosts.annotation.ValidEmail;
import de.pharos.myPosts.annotation.ValidPassword;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@PasswordMatches
public class UserDto {

    @NotBlank
    private String name;

    @NotBlank
    @NewEmail
    @ValidEmail
    private String email;

    @NotBlank
    @ValidPassword
    private String password;

    @NotBlank
    private String confirmPassword;
}