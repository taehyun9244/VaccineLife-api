package com.vaccinelife.vaccinelifeapi.service;

import com.vaccinelife.vaccinelifeapi.model.Ip;
import com.vaccinelife.vaccinelifeapi.repository.IpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional
public class VisitorService {
    private final IpRepository ipRepository;

    public Map<String, Object> visitorCounter() {
        List<Ip> IpList = ipRepository.findAllByOrderByTotalVisitorsDesc();
        Long totalVisitors = IpList.get(0).getTotalVisitors();

        Map<String, Object> visitors = new HashMap<>();
        visitors.put("totalVisitors", totalVisitors);
        visitors.put("todayVisitors", IpList.size());

        return visitors;
    }
}