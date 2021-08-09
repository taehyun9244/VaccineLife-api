package com.vaccinelife.vaccinelifeapi.repository;

import com.vaccinelife.vaccinelifeapi.model.VacBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface VacBoardRepository extends JpaRepository<VacBoard, Long> {
    List<VacBoard> findAllByOrderByCreatedAtDesc();

    List<VacBoard> findTop3ByOrderByLikeCountDescCreatedAtDesc();

    Page<VacBoard> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
