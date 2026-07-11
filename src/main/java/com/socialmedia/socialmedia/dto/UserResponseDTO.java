package com.socialmedia.socialmedia.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
public class UserResponseDTO {
    private String name;
    private String age;
    private List<PostResponseDTO> posts;
}
