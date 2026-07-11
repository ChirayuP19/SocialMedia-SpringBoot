package com.socialmedia.socialmedia.service;

import com.socialmedia.socialmedia.dto.PostResponseDTO;

import java.util.List;

public interface TagService {
     PostResponseDTO createTag(Long postId, List<String> tageName);
}
