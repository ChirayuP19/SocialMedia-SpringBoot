package com.socialmedia.socialmedia.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ProfileRequestDTO {
    private String bio;
    private String avatarUrl;
}
