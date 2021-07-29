package com.vaccinelife.vaccinelifeapi.service;

import com.vaccinelife.vaccinelifeapi.dto.VBoardRequestDto;
import com.vaccinelife.vaccinelifeapi.model.VBoard;
import com.vaccinelife.vaccinelifeapi.repository.VBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class VBoardService {

    private final VBoardRepository vBoardRepository;

    public VBoard getDetailVBoard(Long vboardId){
        VBoard vBoard = vBoardRepository.findById(vboardId).orElseThrow(
                ()-> new IllegalArgumentException("userError")
        );
        return vBoard;
    }
    @Transactional
    public Long update(Long vboardId, VBoardRequestDto requestDto) {
        VBoard vBoard = vBoardRepository.findById(vboardId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        vBoard.update(requestDto);
        return vBoard.getId();
    }

}