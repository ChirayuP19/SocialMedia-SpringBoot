package com.socialmedia.socialmedia.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class AuthRequestDTO {
    @Schema(example = "username")
    @Size(min = 2, max = 100, message = "Username must be between 2 and 100 characters")
    private String username;

    @Schema(example = "password")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String password;
}
