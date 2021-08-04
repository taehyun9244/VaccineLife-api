package com.vaccinelife.vaccinelifeapi.repository;

import com.vaccinelife.vaccinelifeapi.model.Ip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IpRepository extends JpaRepository<Ip, Long> {
    List<Ip> findAllByOrderByTotalVisitorsDesc();
}