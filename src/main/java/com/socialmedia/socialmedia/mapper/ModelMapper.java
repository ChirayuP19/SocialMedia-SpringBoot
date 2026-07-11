package com.socialmedia.socialmedia.mapper;

import com.socialmedia.socialmedia.dto.*;
import com.socialmedia.socialmedia.model.Posts;
import com.socialmedia.socialmedia.model.Tag;
import com.socialmedia.socialmedia.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ModelMapper {

    User requestDTOToUser(UserRequestDTO userRequestDTO);

    @Mapping(source = "posts", target = "posts")
    UserResponseDTO userToResponseDTO(User user);

    Posts requestDTOToPosts(PostRequestDTO postRequestDTO);

    @Mapping(source = "tags", target = "tags")
    PostResponseDTO postsToResponseDTO(Posts posts);

    List<PostResponseDTO> postsToResponseDTOList(List<Posts> posts);

    List<TagResponseDTO> tagsToResponseDTOList(List<Tag> tags);
}
