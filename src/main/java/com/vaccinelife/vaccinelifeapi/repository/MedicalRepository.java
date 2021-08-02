package com.vaccinelife.vaccinelifeapi.repository;

import com.vaccinelife.vaccinelifeapi.model.Medical;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalRepository extends JpaRepository<Medical, Long> {
    List<Medical>findAllByOrderByModifiedAtDesc();
}
