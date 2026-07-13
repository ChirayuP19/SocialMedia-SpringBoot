package com.socialmedia.socialmedia.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserRequestDTO {
    @Column(nullable = false, length = 50)
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @NotBlank(message = "Name cannot be null")
    @Schema(example = "Kavya Patel")
    private String name;

    @Positive(message = "Age must be a positive number")
    @NotNull(message = "Age cannot be null")
    @Schema(example = "25")
    private int age;

    @NotBlank(message = "Password cannot be null")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Schema(example = "password123")
    private String password;
}
