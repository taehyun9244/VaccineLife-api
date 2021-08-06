package com.vaccinelife.vaccinelifeapi.controller;

import com.vaccinelife.vaccinelifeapi.dto.*;
import com.vaccinelife.vaccinelifeapi.model.QuarBoard;
import com.vaccinelife.vaccinelifeapi.service.QuarBoardService;
import com.vaccinelife.vaccinelifeapi.service.QuarCommentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/quarBoard")
public class QuarBoardController {

    private final QuarBoardService quarBoardService;
    private final QuarCommentService quarCommentService;


//    전체 게시판 조회
    @GetMapping("")
    public ResponseEntity<List<QuarBoardSimRequestDto>> getSimpleQuarBoard(){
        return ResponseEntity.ok().body(quarBoardService.getSimpleQuarBoard());
    }

//    탑 3
    @GetMapping("/topLike")
    public ResponseEntity<List<QuarBoardTopRequestDto>> getTopList(){
        return ResponseEntity.ok().body(quarBoardService.getTopList());
    }

//    상세 게시판 조회
    @GetMapping("/{quarBoardId}")
    public ResponseEntity<QuarBoardRequestDto> getDetailVacBoard(@PathVariable Long quarBoardId) {
    quarBoardService.QuarIpChecker(quarBoardId); // 방문자 체크 로직
    return  ResponseEntity.ok().body(quarBoardService.getDetailQuarBoard(quarBoardId));
}

//    게시글 작성
    @PostMapping("")
    public ResponseEntity<Void> createQuarBoard(@RequestBody QuarBoardPostRequestDto requestDto) {
    quarBoardService.createQuarBoard(requestDto);
    return ResponseEntity.created(URI.create("/api/quarBoard")).build();
}


    //    게시글 수정
    @PutMapping("/{quarBoardId}")
    public ResponseEntity<Void> updateVacBoard(@PathVariable Long quarBoardId, @RequestBody QuarBoardRequestDto requestDto) {
        quarBoardService.update(quarBoardId, requestDto);
        return ResponseEntity.ok().build();
    }

    //    게시글 삭제
    @DeleteMapping("/{quarBoardId}")
    public Long deleteQuarBoard(@PathVariable Long quarBoardId) {
        quarBoardService.deleteQuarBoard(quarBoardId);
        return quarBoardId;
    }

    //    댓글 조회
    @GetMapping("/{quarBoardId}/comments")
    public ResponseEntity<List<QuarCommentRequestDto>> getComment(@PathVariable Long quarBoardId) {
        quarCommentService.getQuarComment(quarBoardId);
        return  ResponseEntity.ok().body(quarCommentService.getQuarComment(quarBoardId));
    }


    @GetMapping("/page")
    public Page<QuarBoard> readQuarBoard(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc

    ) {

        page = page - 1;
        return quarBoardService.readQuarBoard(page , size, sortBy, isAsc);
    }
}
