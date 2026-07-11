package com.socialmedia.socialmedia.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class PostResponseDTO {
    private Long id;
    private String title;
    private String content;
    private List<TagResponseDTO> tags;
}

