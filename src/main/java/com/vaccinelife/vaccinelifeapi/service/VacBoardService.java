package com.vaccinelife.vaccinelifeapi.service;

import com.vaccinelife.vaccinelifeapi.dto.VacBoardPostRequsetDto;
import com.vaccinelife.vaccinelifeapi.dto.VacBoardRequestDto;
import com.vaccinelife.vaccinelifeapi.dto.VacBoardSimRequestDto;
import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.model.VacBoard;
import com.vaccinelife.vaccinelifeapi.repository.UserRepository;
import com.vaccinelife.vaccinelifeapi.repository.VacBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VacBoardService {

    private final VacBoardRepository vacBoardRepository;
    private final UserRepository userRepository;
    @Transactional
    public VacBoardRequestDto getDetailVacBoard(Long vacBoardId){
        VacBoard vacBoard = vacBoardRepository.findById(vacBoardId).orElseThrow(
                ()-> new IllegalArgumentException("userError")
        );

        return VacBoardRequestDto.of(vacBoard);
    }
    @Transactional
    public List<VacBoardSimRequestDto> getSimpleVacBorad(){
        List<VacBoard> vacBoards = vacBoardRepository.findAllByOrderByModifiedAtDesc();
        return VacBoardSimRequestDto.list(vacBoards);
    }

    @Transactional
    public void createVacBoard(VacBoardPostRequsetDto requestDto){
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다.")
        );
        VacBoard vacBoard = VacBoard.builder()
                .user(user)
                .title(requestDto.getTitle())
                .contents(requestDto.getContents()).build();
        vacBoardRepository.save(vacBoard);
    }

    @Transactional
    public Long update(Long vBoardId, VacBoardRequestDto requestDto) {
        VacBoard vacBoard = vacBoardRepository.findById(vBoardId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        vacBoard.update(requestDto);
        return vBoardId;
    }

    @Transactional
    public void deleteVacBoard(Long vBoardId){
        VacBoard vacBoard = vacBoardRepository.findById(vBoardId).orElseThrow(
                ()-> new IllegalArgumentException("해당 아이디값을 찾을 수 없습니다.")
        );
        vacBoardRepository.deleteById(vBoardId);
    }

}