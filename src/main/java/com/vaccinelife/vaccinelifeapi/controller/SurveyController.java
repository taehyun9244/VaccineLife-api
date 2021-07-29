package com.vaccinelife.vaccinelifeapi.controller;


import com.vaccinelife.vaccinelifeapi.dto.SurveyRequestDto;
import com.vaccinelife.vaccinelifeapi.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/survey")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;
    @PostMapping("")
    public ResponseEntity<Void> createSurvey(@RequestBody SurveyRequestDto surveyRequestDto){
        surveyService.createSurvey(surveyRequestDto);
        return ResponseEntity.created(URI.create("/api/survey")).build();
    }
}
