package com.socialmedia.socialmedia.service;


import com.socialmedia.socialmedia.dto.ProfileRequestDTO;
import com.socialmedia.socialmedia.dto.ProfileResponseDTO;

public interface ProfileService {
    ProfileResponseDTO createProfile(Long userId, ProfileRequestDTO profileRequestDTO);
}
