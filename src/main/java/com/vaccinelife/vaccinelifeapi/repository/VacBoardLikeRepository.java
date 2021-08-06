package com.vaccinelife.vaccinelifeapi.repository;

import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.model.VacBoard;
import com.vaccinelife.vaccinelifeapi.model.VacBoardLike;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacBoardLikeRepository extends JpaRepository<VacBoardLike, Long> {

    boolean existsByVacBoardAndUser(VacBoard vacBoard, User user);

    void deleteByVacBoardAndUser(VacBoard vacBoard, User user);

    @EntityGraph(attributePaths = ("vacBoard"))
    List<VacBoardLike> findByUser(VacBoardLike user);

    List<VacBoardLike> findVacBoardLikeByUser(VacBoardLike user);

    List<VacBoardLike> findAllByUserId(Long id);
}