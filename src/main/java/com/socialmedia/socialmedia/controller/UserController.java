package com.socialmedia.socialmedia.controller;

import com.socialmedia.socialmedia.dto.UserRequestDTO;
import com.socialmedia.socialmedia.dto.UserResponseDTO;
import com.socialmedia.socialmedia.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;


    @PostMapping("")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Validated UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userService.createUser(userRequestDTO);
        log.info("User created with name: {} and age: {}", userResponseDTO.getName(), userResponseDTO.getAge());
        return ResponseEntity.ok(userResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO userResponseDTO = userService.getUserById(id);
        log.info("Retrieved user with id: {}", id);
        return ResponseEntity.ok(userResponseDTO);
    }

}
