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

    Page<VacBoardSimRequestDto> findAllByOrderByCreatedAtDesc(Pageable pageable); //페이지네이션 + 최신순
    Page<VacBoardSimRequestDto> findAllByUserTypeOrderByCreatedAtDesc(Pageable pageable, String type); //페이지네이션 + 유저-백신 타입으로 조회 + 최신순으로.
    VacBoard findTopByIdLessThanOrderByCreatedAtDesc(Long vacBoardId);
    VacBoard findFirstByIdGreaterThan(Long vacBoardId);
}