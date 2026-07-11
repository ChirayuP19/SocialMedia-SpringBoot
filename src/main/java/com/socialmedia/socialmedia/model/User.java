package com.socialmedia.socialmedia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    @CreationTimestamp()
    @Column(updatable = false)
    private LocalDateTime createAt = LocalDateTime.now(ZoneId.systemDefault());

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updatedAt = LocalDateTime.now(ZoneId.systemDefault());

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Posts> posts;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Profile profile;
}
