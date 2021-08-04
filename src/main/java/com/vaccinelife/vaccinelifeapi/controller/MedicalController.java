package com.vaccinelife.vaccinelifeapi.controller;

import com.vaccinelife.vaccinelifeapi.dto.MedicalRequestDto;
import com.vaccinelife.vaccinelifeapi.model.Medical;
import com.vaccinelife.vaccinelifeapi.repository.MedicalRepository;
import com.vaccinelife.vaccinelifeapi.service.MedicalService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController

public class MedicalController {

    private final MedicalService medicalService;
    private final MedicalRepository medicalRepository;

    //    의료진 한마디 조회
    @GetMapping("/api/medical")
    public ResponseEntity<List<Medical>> getMedicalRequestDto() {
        return ResponseEntity.ok().body(medicalService.getMedicalRequestDto());
    }


    // 의료진 한마디 작성
    @PostMapping("/api/medical")
    public ResponseEntity<Void> createMedical(@RequestBody MedicalRequestDto requestDto) {
        medicalService.createMedical(requestDto);
        return ResponseEntity.created(URI.create("/api/medical")).build();
    }


    //    의료진 한마디 삭제
    @DeleteMapping("/api/medical/{medicalId}")
    public Long deleteMedical(@PathVariable Long medicalId) {
        medicalService.deleteMedical(medicalId);
        return medicalId;
    }



}
