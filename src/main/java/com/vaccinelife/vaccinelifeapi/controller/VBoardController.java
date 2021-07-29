package com.vaccinelife.vaccinelifeapi.controller;

import com.vaccinelife.vaccinelifeapi.dto.VBoardPostRequsetDto;
import com.vaccinelife.vaccinelifeapi.dto.VBoardRequestDto;
import com.vaccinelife.vaccinelifeapi.dto.VBoardSimRequestDto;
import com.vaccinelife.vaccinelifeapi.model.VBoard;
import com.vaccinelife.vaccinelifeapi.repository.VBoardRepository;
import com.vaccinelife.vaccinelifeapi.service.VBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/vBoard")
public class VBoardController {
    private final VBoardRepository vBoardRepository;
    private final VBoardService vBoardService;

    //    전체 게시판 조회
    @GetMapping("")
    public ResponseEntity<List<VBoardSimRequestDto>> getSimpleVBorad(){
        return ResponseEntity.ok().body(vBoardService.getSimpleVBorad());
    }

    //    상세 게시판 조회
    @GetMapping("/{vBoardId}")
    public ResponseEntity<VBoardRequestDto> getDetailVBoard(@PathVariable Long vBoardId) {
        return  ResponseEntity.ok().body(vBoardService.getDetailVBoard(vBoardId));
    }

    //    게시글 작성
    @PostMapping("")
    public ResponseEntity<Void> createVBoard(@RequestBody VBoardPostRequsetDto requestDto) {
        vBoardService.createVBoard(requestDto);
        return ResponseEntity.created(URI.create("/api/vBoard")).build();
    }

    //    게시글 수정
    @PutMapping("/{vBoardId}")
    public ResponseEntity<Void> updateVBoard(@PathVariable Long vBoardId, @RequestBody VBoardRequestDto requestDto) {
        vBoardService.update(vBoardId, requestDto);
        return ResponseEntity.ok().build();
    }

    //    게시글 삭제
    @DeleteMapping("/{vBoardId}")
    public Long deleteVBoraed(@PathVariable Long vBoardId) {
        vBoardService.deleteVBoard(vBoardId);
        return vBoardId;
    }
}
