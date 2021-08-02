package com.vaccinelife.vaccinelifeapi.controller;

import com.vaccinelife.vaccinelifeapi.dto.VacBoardPostRequsetDto;
import com.vaccinelife.vaccinelifeapi.dto.VacBoardRequestDto;
import com.vaccinelife.vaccinelifeapi.dto.VacBoardSimRequestDto;
import com.vaccinelife.vaccinelifeapi.repository.VacBoardRepository;
import com.vaccinelife.vaccinelifeapi.service.VacBoardService;
import com.vaccinelife.vaccinelifeapi.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/vacBoard")

public class VacBoardController {
    private final VacBoardService vacBoardService;
//    private final VisitorService visitorService;
    //    전체 게시판 조회
    @GetMapping("")
    public ResponseEntity<List<VacBoardSimRequestDto>> getSimpleVacBorad(){
        return ResponseEntity.ok().body(vacBoardService.getSimpleVacBorad());
    }

//    @GetMapping("api/visitors")
//    public Map<String, Object> getVacBoard() {
//        return visitorService.visitorCounter();
//    }

    //    상세 게시판 조회
    @GetMapping("/{vacBoardId}")
    public ResponseEntity<VacBoardRequestDto> getDetailVacBoard(@PathVariable Long vacBoardId) {
//        vacBoardService.IpChecker(); // 방문자 체크 로직
        return  ResponseEntity.ok().body(vacBoardService.getDetailVacBoard(vacBoardId));
    }

    //    게시글 작성
    @PostMapping("")
    public ResponseEntity<Void> createVacBoard(@RequestBody VacBoardPostRequsetDto requestDto) {
        vacBoardService.createVacBoard(requestDto);
        return ResponseEntity.created(URI.create("/api/vacBoard")).build();
    }

    //    게시글 수정
    @PutMapping("/{vacBoardId}")
    public ResponseEntity<Void> updateVacBoard(@PathVariable Long vacBoardId, @RequestBody VacBoardRequestDto requestDto) {
        vacBoardService.update(vacBoardId, requestDto);
        return ResponseEntity.ok().build();
    }

    //    게시글 삭제
    @DeleteMapping("/{vacBoardId}")
    public Long deleteVacBoard(@PathVariable Long vacBoardId) {
        vacBoardService.deleteVacBoard(vacBoardId);
        return vacBoardId;
    }
}