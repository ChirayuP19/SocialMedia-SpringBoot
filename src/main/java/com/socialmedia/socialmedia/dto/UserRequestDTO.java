package com.socialmedia.socialmedia.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserRequestDTO {
    private String name;
    private String age;
}
