package com.vaccinelife.vaccinelifeapi.controller;

import com.vaccinelife.vaccinelifeapi.dto.*;
import com.vaccinelife.vaccinelifeapi.exception.ApiException;
import com.vaccinelife.vaccinelifeapi.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/mypage")
public class MyPageController {

    private final VacBoardService vacBoardService;
    private final QuarBoardService quarBoardService;
    private final MedicalService medicalService;


    //백신 후기 List 받기
    @GetMapping("/{userId}/vacBoard")
    public ResponseEntity<List<VacBoardSimRequestDto>> getMypageVacBoard(@PathVariable Long userId) {
        return ResponseEntity.ok().body(vacBoardService.getMypageVacBoard(userId));
    }

    //자가 격리 List 받기
    @GetMapping("/{userId}/quarBoard")
    public ResponseEntity<List<QuarBoardSimRequestDto>> getMypageQuarBoard(@PathVariable Long userId){
        return ResponseEntity.ok().body(quarBoardService.getMypageQuarBoard(userId));
    }

    //의료진 분들께 한마디 List 받기
    @GetMapping("/{userId}/medical")
    public ResponseEntity<List<MedicalResponseDto>> getMypageMedical(@PathVariable Long userId){
        return ResponseEntity.ok().body(medicalService.getMypageMedical(userId));
    }



    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> handle(IllegalArgumentException ex) {
        ApiException apiException = new ApiException(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(
                apiException,
                HttpStatus.BAD_REQUEST
        );
    }
}
