package com.vaccinelife.vaccinelifeapi.repository;

import com.vaccinelife.vaccinelifeapi.dto.QuarBoardSimRequestDto;
import com.vaccinelife.vaccinelifeapi.model.QuarBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface QuarBoardRepository extends JpaRepository<QuarBoard, Long> {
   List<QuarBoard> findAllByOrderByCreatedAtDesc();
   List<QuarBoard> findAllByUserIdOrderByCreatedAtDesc(Long userId);
   List<QuarBoard> findTop3ByCreatedAtBetweenOrderByLikeCountDescCreatedAtDesc(LocalDateTime created, LocalDateTime week);

   Page<QuarBoardSimRequestDto> findAllByOrderByCreatedAtDesc(Pageable pageable); //페이지네이션 + 최신순

   QuarBoard findTopByIdLessThanOrderByCreatedAtDesc(Long querBoardId);
   QuarBoard findFirstByIdGreaterThan(Long querBoardId);

}
