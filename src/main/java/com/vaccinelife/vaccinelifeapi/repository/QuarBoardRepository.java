package com.vaccinelife.vaccinelifeapi.repository;

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

   Page<QuarBoard> findAllByOrderByCreatedAtDesc(Pageable pageable);

   QuarBoard findTopByIdLessThanOrderByCreatedAtDesc(Long querBoardId);
   QuarBoard findFirstByIdGreaterThan(Long querBoardId);

}
