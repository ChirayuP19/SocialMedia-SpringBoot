package com.socialmedia.socialmedia.service;


import com.socialmedia.socialmedia.dto.UserRequestDTO;
import com.socialmedia.socialmedia.dto.UserResponseDTO;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    UserResponseDTO getUserById(Long id);
}
