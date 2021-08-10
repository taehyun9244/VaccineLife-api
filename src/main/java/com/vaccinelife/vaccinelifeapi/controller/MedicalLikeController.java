package com.vaccinelife.vaccinelifeapi.controller;

import com.vaccinelife.vaccinelifeapi.dto.MedicalLikeRequestDto;
import com.vaccinelife.vaccinelifeapi.dto.QuarBoardLikeRequestDto;
import com.vaccinelife.vaccinelifeapi.dto.ResponseDto;
import com.vaccinelife.vaccinelifeapi.exception.ApiException;
import com.vaccinelife.vaccinelifeapi.service.MedicalLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical/like")
@RequiredArgsConstructor
public class MedicalLikeController {
    private final MedicalLikeService medicalLikeService;

    //좋아요 or 좋아요 취소
    @PostMapping("")
    public ResponseDto Like(@RequestBody MedicalLikeRequestDto requestDto) {
        return medicalLikeService.Like(requestDto);
    }

    //유저별 좋아요 medicalId 받기
    @GetMapping("/{userId}")
    public ResponseEntity<List<MedicalLikeRequestDto>> getLike(@PathVariable Long userId) {
        return ResponseEntity.ok().body(medicalLikeService.getLike(userId));
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
