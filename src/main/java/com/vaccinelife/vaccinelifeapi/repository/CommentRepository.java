package com.vaccinelife.vaccinelifeapi.repository;
import com.vaccinelife.vaccinelifeapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllById(Long id);
    List<Comment> findByVacBoardId(Long id);
//    List<Comment> findAllByVBoardIdOrderByCreatedAtDesc(Long vBoardId);
}
