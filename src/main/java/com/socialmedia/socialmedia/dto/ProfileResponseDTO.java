package com.socialmedia.socialmedia.dto;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProfileResponseDTO {
    private Long id;
    private String bio;
    private String avatarUrl;
    private String name;
    private int age;
    private LocalDateTime createAt;
}
