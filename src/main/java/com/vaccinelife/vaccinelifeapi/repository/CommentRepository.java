package com.vaccinelife.vaccinelifeapi.repository;

import com.vaccinelife.vaccinelifeapi.model.Comment;
import com.vaccinelife.vaccinelifeapi.model.VBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllById(Long vBoardId);
}
