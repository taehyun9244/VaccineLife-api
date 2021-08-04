//package com.vaccinelife.vaccinelifeapi.controller;
//
//
//import com.vaccinelife.vaccinelifeapi.service.VisitorService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.Map;
//
//@RequiredArgsConstructor
//@Controller
//public class VisitorController {
//    private final VisitorService visitorService;
//
//    @GetMapping("api/vacBoard/visitors")
//    public Map<String, Object> getVacBoard() {
//        return visitorService.visitorCounter();
//    }
//
//
//
//}