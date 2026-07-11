package com.socialmedia.socialmedia.controller;

import com.socialmedia.socialmedia.dto.ProfileRequestDTO;
import com.socialmedia.socialmedia.dto.ProfileResponseDTO;
import com.socialmedia.socialmedia.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
@Slf4j
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/{userId}/create")
    public ResponseEntity<ProfileResponseDTO> createProfile(
            @PathVariable Long userId,
            @RequestBody ProfileRequestDTO profileRequestDTO) {
        ProfileResponseDTO profile = profileService.createProfile(userId, profileRequestDTO);
        return ResponseEntity.ok(profile);
    }
}
