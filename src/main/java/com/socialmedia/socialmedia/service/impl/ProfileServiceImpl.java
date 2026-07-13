package com.socialmedia.socialmedia.service.impl;

import com.socialmedia.socialmedia.dto.ProfileRequestDTO;
import com.socialmedia.socialmedia.dto.ProfileResponseDTO;
import com.socialmedia.socialmedia.model.Profile;
import com.socialmedia.socialmedia.model.User;
import com.socialmedia.socialmedia.repository.UserRepository;
import com.socialmedia.socialmedia.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;

    @Override
    public ProfileResponseDTO createProfile(Long userId, ProfileRequestDTO profileRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        User jwtUser = (User) authentication.getPrincipal();
        if (user.getProfile() == null ||
                !Objects.equals(user.getId(), Objects.requireNonNull(jwtUser).getId())
                        && !jwtUser.getRole().equals("ADMIN")) {
            assert jwtUser != null;
            log.warn("User with id: {} is trying to create a profile for " +
                    "user with id: {} without permission", jwtUser.getId(), user.getId());
            throw new IllegalArgumentException("Profile already exists for this user or " +
                    "you are not the owner");
        }
        log.info("Creating profile for user with id: {}", userId);
        Profile profile = Profile.builder()
                .bio(profileRequestDTO.getBio())
                .avatarUrl(profileRequestDTO.getAvatarUrl())
                .user(user)
                .build();

        user.setProfile(profile);
        userRepository.save(user);
        log.info("Profile created for user with id: {}", userId);
        return ProfileResponseDTO.builder()
                .id(user.getId())
                .bio(user.getProfile().getBio())
                .age(user.getAge())
                .name(user.getName())
                .createAt(profile.getCreateAt())
                .avatarUrl(user.getProfile().getAvatarUrl()).build();
    }
}
