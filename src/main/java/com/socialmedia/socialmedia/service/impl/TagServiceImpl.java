package com.socialmedia.socialmedia.service.impl;

import com.socialmedia.socialmedia.dto.PostResponseDTO;
import com.socialmedia.socialmedia.mapper.ModelMapper;
import com.socialmedia.socialmedia.model.Posts;
import com.socialmedia.socialmedia.model.Tag;
import com.socialmedia.socialmedia.repository.PostRepository;
import com.socialmedia.socialmedia.repository.TagRepository;
import com.socialmedia.socialmedia.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final PostRepository postRepository;
    private final ModelMapper mapper;


    @Override
    public PostResponseDTO createTag(Long postId, List<String> tageNames) {
        log.info("Creating tag with name: {}", tageNames);
        Posts post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        for (String tagName : tageNames) {
            Tag tag = tagRepository.findByName(tagName).orElseGet(() -> {
                Tag newTag = new Tag();
                newTag.setName(tagName);
                return tagRepository.save(newTag);
            });
            post.getTags().add(tag);
        }
        postRepository.save(post);
        return mapper.postsToResponseDTO(post);
    }
}
