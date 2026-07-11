package com.socialmedia.socialmedia.repository;

import com.socialmedia.socialmedia.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {

    Optional<Posts> findById(Long id);
}
