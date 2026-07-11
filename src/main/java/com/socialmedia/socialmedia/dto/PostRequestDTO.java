package com.socialmedia.socialmedia.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class PostRequestDTO {
    private String title;
    private String content;
}
