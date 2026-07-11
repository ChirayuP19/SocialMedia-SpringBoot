package com.socialmedia.socialmedia.service.impl;

import com.socialmedia.socialmedia.dto.UserRequestDTO;
import com.socialmedia.socialmedia.dto.UserResponseDTO;
import com.socialmedia.socialmedia.mapper.ModelMapper;
import com.socialmedia.socialmedia.model.User;
import com.socialmedia.socialmedia.repository.UserRepository;
import com.socialmedia.socialmedia.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        log.info("Creating user with name: {} and age: {}", userRequestDTO.getName(), userRequestDTO.getAge());
        User save = userRepository.save(modelMapper.requestDTOToUser(userRequestDTO));
        log.info("User created with id: {} at {}", save.getId(), save.getCreateAt());
        return modelMapper.userToResponseDTO(save);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        log.info("Getting user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        log.info("User: {}", user.getName());
        log.info("Posts: {}", user.getPosts());
        log.info("Posts size: {}", user.getPosts().size());
        return modelMapper.userToResponseDTO(user);
    }
}
