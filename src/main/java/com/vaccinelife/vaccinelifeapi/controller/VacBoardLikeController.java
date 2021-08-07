package com.vaccinelife.vaccinelifeapi.controller;

import com.vaccinelife.vaccinelifeapi.dto.LikeRequestDto;
import com.vaccinelife.vaccinelifeapi.dto.ResponseDto;
import com.vaccinelife.vaccinelifeapi.exception.ApiException;
import com.vaccinelife.vaccinelifeapi.service.VacBoardLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VacBoardLikeController {

    private final VacBoardLikeService vacBoardLikeService;

    //    좋아요 누르기
    @PostMapping("/api/vacBoard/like")
    public ResponseDto Like(@RequestBody LikeRequestDto likeRequestDto) {
        return vacBoardLikeService.Like(likeRequestDto);
    }

    //    좋아요 조회
    @GetMapping("/api/vacBoard/like/{userId}")
    public ResponseEntity<List<LikeRequestDto>> Like(@PathVariable Long userId) {
        return ResponseEntity.ok().body(vacBoardLikeService.getLike(userId));
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