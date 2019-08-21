package de.pharos.myPosts.dto;

import de.pharos.myPosts.annotation.ValidStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PostDto {

    @NotBlank
    private String content;

    @ValidStatus
    private String status = "PUBLIC";
}