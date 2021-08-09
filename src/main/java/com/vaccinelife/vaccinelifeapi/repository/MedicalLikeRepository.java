package com.vaccinelife.vaccinelifeapi.repository;

import com.vaccinelife.vaccinelifeapi.model.Medical;
import com.vaccinelife.vaccinelifeapi.model.MedicalLike;
import com.vaccinelife.vaccinelifeapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalLikeRepository extends JpaRepository<MedicalLike, Long> {

    boolean existsByMedicalAndUser(Medical medical, User user);

    void deleteByMedicalAndUser(Medical medical, User user);

    List<MedicalLike> findAllByUserId(Long id);
}
