package com.vaccinelife.vaccinelifeapi.repository;

import com.vaccinelife.vaccinelifeapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaAuditing
@EnableJpaRepositories
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findById(Long id);

}
