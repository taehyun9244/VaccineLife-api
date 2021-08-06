package com.vaccinelife.vaccinelifeapi.repository;

import com.vaccinelife.vaccinelifeapi.model.*;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuarBoardLikeRepository extends JpaRepository<QuarBoardLike, Long> {

    boolean existsByQuarBoardAndUser(QuarBoard quarBoard, User user);

    void deleteByQuarBoardAndUser(QuarBoard quarBoard, User user);

    @EntityGraph(attributePaths = ("quarBoard"))
    List<QuarBoardLike> findByUser(QuarBoardLike user);

    List<QuarBoardLike> findQuarBoardLikeByUser(QuarBoardLike user);

    List<QuarBoardLike> findAllByUserId(Long id);
}

