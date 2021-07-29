package com.vaccinelife.vaccinelifeapi.controller;

import com.vaccinelife.vaccinelifeapi.dto.VBoardRequestDto;
import com.vaccinelife.vaccinelifeapi.model.VBoard;
import com.vaccinelife.vaccinelifeapi.repository.VBoardRepository;
import com.vaccinelife.vaccinelifeapi.service.VBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
public class VBoardController {
    private final VBoardRepository vBoardRepository;
    private final VBoardService vBoardService;

//    전체 게시판 조회
    @GetMapping("/api/vboard")
    public List<VBoard> getVBoard(){
        return vBoardRepository.findAllByOrderByModifiedAtDesc();
    }
//    상세 게시판 조회
    @GetMapping("/api/vboard/{vboardId}")
    public VBoard getDetailVBoard(@PathVariable Long vboardId){
        return vBoardService.getDetailVBoard(vboardId);
    }
//    게시글 작성
    @PostMapping("/api/vboard")
    public VBoard createVBoard(@PathVariable VBoardRequestDto requestDto){
        VBoard vBoard = new VBoard(requestDto);
        return vBoardRepository.save(vBoard);
    }
//    게시글 수정
    @PutMapping("/api/vboard/{vboardId}")
    public Long updateVBoard(@PathVariable Long vboardId, @RequestBody VBoardRequestDto requestDto){
        vBoardService.update(vboardId, requestDto);
        return vboardId;
    }
//    게시글 삭제
    @DeleteMapping("/api/vboard/{vboardId}")
    public Long deleteVBoraed(@PathVariable Long vboardId){
        vBoardRepository.deleteById(vboardId);
        return vboardId;
    }
}
