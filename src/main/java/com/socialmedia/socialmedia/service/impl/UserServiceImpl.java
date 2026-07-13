package com.socialmedia.socialmedia.service.impl;

import com.socialmedia.socialmedia.dto.UserRequestDTO;
import com.socialmedia.socialmedia.dto.UserResponseDTO;
import com.socialmedia.socialmedia.exception.ResourceNotFoundException;
import com.socialmedia.socialmedia.exception.UserAlreadyExistsException;
import com.socialmedia.socialmedia.mapper.ModelMapper;
import com.socialmedia.socialmedia.model.User;
import com.socialmedia.socialmedia.repository.UserRepository;
import com.socialmedia.socialmedia.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private static final String USER_ROLE = "USER";

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        if (userRepository.findByName(userRequestDTO.getName()).isPresent()) {
            throw new UserAlreadyExistsException("User with name " + userRequestDTO.getName() + " already exists");
        }
        log.info("Creating user with name: {} and age: {}", userRequestDTO.getName(), userRequestDTO.getAge());
        User user = modelMapper.requestDTOToUser(userRequestDTO);
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setRole(USER_ROLE);
        User save = userRepository.save(user);
        log.info("User created with id: {} at {}", save.getId(), save.getCreateAt());
        return modelMapper.userToResponseDTO(save);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        User loginUser = (User) authentication.getPrincipal();
        assert loginUser != null;
        if (!Objects.equals(loginUser.getId(), id) && !loginUser.getRole().equals("ADMIN")) {
            log.warn("User with id: {} is trying to access user with id: {} without permission", loginUser.getId(), id);
            throw new AccessDeniedException("User with id: " +
                    loginUser.getId() + " is trying to access user with id: " + id
                    + " without permission");
        }
        log.info("Getting user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        log.info("User: {}", user.getName());
        log.info("Posts: {}", user.getPosts());
        log.info("Posts size: {}", user.getPosts().size());
        return modelMapper.userToResponseDTO(user);
    }
}
