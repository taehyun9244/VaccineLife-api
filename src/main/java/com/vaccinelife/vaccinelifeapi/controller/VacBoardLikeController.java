package com.vaccinelife.vaccinelifeapi.controller;

import com.vaccinelife.vaccinelifeapi.dto.LikeRequestDto;
import com.vaccinelife.vaccinelifeapi.dto.ResponseDto;
import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.model.VacBoard;
import com.vaccinelife.vaccinelifeapi.model.VacBoardLike;
import com.vaccinelife.vaccinelifeapi.repository.VacBoardLikeRepository;
import com.vaccinelife.vaccinelifeapi.service.VacBoardLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class VacBoardLikeController {

    private final VacBoardLikeService vacBoardLikeService;
    private final VacBoardLikeRepository vacBoardLikeRepository;

    @PostMapping("/api/like")
    public ResponseDto Like(@RequestBody LikeRequestDto likeRequestDto) {
        return vacBoardLikeService.Like(likeRequestDto);
    }
    @GetMapping("/api/like/{userId}")
    public ResponseEntity<List<LikeRequestDto>> Like(@PathVariable Long userId) {
        return ResponseEntity.ok().body(vacBoardLikeService.getLike(userId));
    }

}