package com.socialmedia.socialmedia.service.impl;

import com.socialmedia.socialmedia.dto.PostRequestDTO;
import com.socialmedia.socialmedia.dto.PostResponseDTO;
import com.socialmedia.socialmedia.mapper.ModelMapper;
import com.socialmedia.socialmedia.model.Posts;
import com.socialmedia.socialmedia.model.User;
import com.socialmedia.socialmedia.repository.PostRepository;
import com.socialmedia.socialmedia.repository.UserRepository;
import com.socialmedia.socialmedia.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostsServiceImpl implements PostsService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public PostResponseDTO createPost(Long userId, PostRequestDTO postRequestDTO) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        log.info("User found with id: {} and name: {}", user.getId(), user.getName());

        log.info("Creating post with title: {} and content: {}", postRequestDTO.getTitle(), postRequestDTO.getContent());
        Posts posts = modelMapper.requestDTOToPosts(postRequestDTO);
        posts.setUser(user);
        Posts save = postRepository.save(posts);
        log.info("Post created with id: {} at {}", save.getId(), save.getCreatedAt());
        return modelMapper.postsToResponseDTO(save);
    }
}
