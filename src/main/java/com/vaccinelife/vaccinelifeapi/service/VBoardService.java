package com.vaccinelife.vaccinelifeapi.service;

import com.vaccinelife.vaccinelifeapi.dto.VBoardPostRequsetDto;
import com.vaccinelife.vaccinelifeapi.dto.VBoardRequestDto;
import com.vaccinelife.vaccinelifeapi.dto.VBoardSimRequestDto;
import com.vaccinelife.vaccinelifeapi.model.VBoard;
import com.vaccinelife.vaccinelifeapi.repository.VBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VBoardService {

    private final VBoardRepository vBoardRepository;

    @Transactional
    public VBoardRequestDto getDetailVBoard(Long vBoardId){
        VBoard vBoard = vBoardRepository.findById(vBoardId).orElseThrow(
                ()-> new IllegalArgumentException("userError")
        );
        return VBoardRequestDto.of(vBoard);
    }
    @Transactional
    public List<VBoardSimRequestDto> getSimpleVBorad(){
        List<VBoard> vBoards = vBoardRepository.findAllByOrderByModifiedAtDesc();
        return VBoardSimRequestDto.list(vBoards);
    }

    @Transactional
    public void createVBoard(VBoardPostRequsetDto requestDto){
        VBoard vBoard = new VBoard(requestDto);
        vBoardRepository.save(vBoard);
    }

    @Transactional
    public Long update(Long vBoardId, VBoardRequestDto requestDto) {
        VBoard vBoard = vBoardRepository.findById(vBoardId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        vBoard.update(requestDto);
        return vBoardId;
    }

    @Transactional
    public void deleteVBoard(Long vBoardId){
        VBoard vBoard = vBoardRepository.findById(vBoardId).orElseThrow(
        ()-> new IllegalArgumentException("해당 아이디값을 찾을 수 없습니다.")
        );
        vBoardRepository.deleteById(vBoardId);
    }

}