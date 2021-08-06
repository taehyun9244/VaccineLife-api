package com.vaccinelife.vaccinelifeapi.controller;

import com.vaccinelife.vaccinelifeapi.dto.QuarBoardLikeRequestDto;
import com.vaccinelife.vaccinelifeapi.dto.ResponseDto;
import com.vaccinelife.vaccinelifeapi.service.QuarBoardLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuarBoardLikeController {

    private final QuarBoardLikeService quarBoardLikeService;

//    좋아요 누르기
    @PostMapping("/api/quarBoard/like")
    public ResponseDto Like(@RequestBody QuarBoardLikeRequestDto requestDto) {
        return quarBoardLikeService.Like(requestDto);
    }

//    좋아요 조회
    @GetMapping("/api/quarBoard/like/{userId}")
    public ResponseEntity<List<QuarBoardLikeRequestDto>> Like(@PathVariable Long userId) {
        return ResponseEntity.ok().body(quarBoardLikeService.getLike(userId));
    }
}
