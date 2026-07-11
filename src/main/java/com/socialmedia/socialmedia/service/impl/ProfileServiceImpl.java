package com.socialmedia.socialmedia.service.impl;

import com.socialmedia.socialmedia.dto.ProfileRequestDTO;
import com.socialmedia.socialmedia.dto.ProfileResponseDTO;
import com.socialmedia.socialmedia.model.Profile;
import com.socialmedia.socialmedia.model.User;
import com.socialmedia.socialmedia.repository.UserRepository;
import com.socialmedia.socialmedia.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;

    @Override
    public ProfileResponseDTO createProfile(Long userId, ProfileRequestDTO profileRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getProfile() != null)
            throw new IllegalArgumentException("Profile already exists for this user");

        Profile profile = Profile.builder()
                .bio(profileRequestDTO.getBio())
                .avatarUrl(profileRequestDTO.getAvatarUrl())
                .user(user)
                .build();

        user.setProfile(profile);
        userRepository.save(user);
        return ProfileResponseDTO.builder()
                .id(user.getId())
                .bio(user.getProfile().getBio())
                .age(user.getAge())
                .name(user.getName())
                .createAt(profile.getCreateAt())
                .avatarUrl(user.getProfile().getAvatarUrl()).build();
    }
}
