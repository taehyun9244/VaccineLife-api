package com.vaccinelife.vaccinelifeapi.repository;

import com.vaccinelife.vaccinelifeapi.model.Ip;
import com.vaccinelife.vaccinelifeapi.model.QuarBoard;
import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.model.VacBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IpRepository extends JpaRepository<Ip, Long> {

    boolean existsByVacBoardAndIp(VacBoard vacBoard, String Ip);
    boolean existsByQuarBoardAndIp(QuarBoard quarBoard, String Ip);


}