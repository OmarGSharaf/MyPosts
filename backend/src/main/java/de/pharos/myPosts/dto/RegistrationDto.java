package de.pharos.myPosts.dto;

import lombok.Data;

@Data
public class RegistrationDto {

    private String name;

    private String email;

    private String token;
}