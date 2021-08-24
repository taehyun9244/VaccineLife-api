package com.vaccinelife.vaccinelifeapi.repository;

import com.vaccinelife.vaccinelifeapi.dto.VacBoardSimRequestDto;
import com.vaccinelife.vaccinelifeapi.model.VacBoard;
import javassist.runtime.Desc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VacBoardRepository extends JpaRepository<VacBoard, Long> {
    List<VacBoard> findAllByOrderByCreatedAtDesc();
    List<VacBoard> findAllByUserIdOrderByCreatedAtDesc(Long userId);

    List<VacBoard> findTop3ByCreatedAtBetweenOrderByLikeCountDescCreatedAtDesc(LocalDateTime created, LocalDateTime week);

    Page<VacBoardSimRequestDto> findAllByOrderByCreatedAtDesc(Pageable pageable);

    VacBoard findTopByIdLessThanOrderByCreatedAtDesc(Long vacBoardId);
    VacBoard findFirstByIdGreaterThan(Long vacBoardId);
}