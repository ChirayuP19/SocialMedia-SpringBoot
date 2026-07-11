package com.socialmedia.socialmedia.controller;

import com.socialmedia.socialmedia.dto.PostResponseDTO;
import com.socialmedia.socialmedia.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
@Slf4j
public class TagController {

    private final TagService tagService;

    @PostMapping("/{postId}")
    public PostResponseDTO createTag(@PathVariable Long postId,
                                     @RequestBody List<String> tageNames) {
        return tagService.createTag(postId, tageNames);
    }
}
