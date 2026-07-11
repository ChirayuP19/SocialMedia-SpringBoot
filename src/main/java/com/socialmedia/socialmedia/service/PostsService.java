package com.socialmedia.socialmedia.service;

import com.socialmedia.socialmedia.dto.PostRequestDTO;
import com.socialmedia.socialmedia.dto.PostResponseDTO;

public interface PostsService {

    PostResponseDTO createPost(Long userId,PostRequestDTO postRequestDTO);
}
