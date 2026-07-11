package com.socialmedia.socialmedia.controller;


import com.socialmedia.socialmedia.dto.PostRequestDTO;
import com.socialmedia.socialmedia.dto.PostResponseDTO;
import com.socialmedia.socialmedia.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostsService postsService;

    @PostMapping("/{userId}/create")
    public ResponseEntity<PostResponseDTO> createPosts(
            @PathVariable Long userId,
            @RequestBody PostRequestDTO postRequestDTO) {
        log.info("Creating post with title: {} and content: {}", postRequestDTO.getTitle(), postRequestDTO.getContent());
        return ResponseEntity.ok(postsService.createPost(userId,postRequestDTO));
    }
}
