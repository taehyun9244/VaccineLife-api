package com.vaccinelife.vaccinelifeapi.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.vaccinelife.vaccinelifeapi.dto.VacBoardPostRequestDto;
import com.vaccinelife.vaccinelifeapi.dto.VacBoardRequestDto;
import com.vaccinelife.vaccinelifeapi.dto.VacBoardSimRequestDto;
import com.vaccinelife.vaccinelifeapi.model.Ip;
import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.model.VacBoard;
import com.vaccinelife.vaccinelifeapi.repository.IpRepository;
import com.vaccinelife.vaccinelifeapi.repository.UserRepository;
import com.vaccinelife.vaccinelifeapi.repository.VacBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VacBoardService {

    private final VacBoardRepository vacBoardRepository;
    private final UserRepository userRepository;
    private final IpRepository ipRepository;


//    상세조회
    @Transactional
    public VacBoardRequestDto getDetailVacBoard(Long vacBoardId){
        VacBoard vacBoard = vacBoardRepository.findById(vacBoardId).orElseThrow(
                ()-> new IllegalArgumentException("userError")
        );

        return VacBoardRequestDto.of(vacBoard);
    }
//    전체조회
    @Transactional
    public List<VacBoardSimRequestDto> getSimpleVacBoard(){
        List<VacBoard> vacBoards = vacBoardRepository.findAllByOrderByCreatedAtDesc();
        return VacBoardSimRequestDto.list(vacBoards);
    }

//    탑 3
    @Transactional
    public List<VacBoardSimRequestDto> getTopList(){
        List<VacBoard> vacBoards = vacBoardRepository.findTop3ByOrderByLikeCountDescCreatedAtDesc();
        return VacBoardSimRequestDto.list(vacBoards);
    }

// 게시물 작성
    @Transactional
    public void createVacBoard(VacBoardPostRequestDto requestDto){
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다.")
        );
        VacBoard vacBoard = VacBoard.builder()
                .user(user)
                .title(requestDto.getTitle())
                .contents(requestDto.getContents()).build();
        vacBoardRepository.save(vacBoard);
    }
// 게시물 수정
    @Transactional
    public Long update(Long vacBoardId, VacBoardRequestDto requestDto) {
        VacBoard vacBoard = vacBoardRepository.findById(vacBoardId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        vacBoard.update(requestDto);
        return vacBoardId;
    }
// 게시물 삭제
    @Transactional
    public void deleteVacBoard(Long vacBoardId){
        VacBoard vacBoard = vacBoardRepository.findById(vacBoardId).orElseThrow(
                ()-> new IllegalArgumentException("해당 아이디값을 찾을 수 없습니다.")
        );
        vacBoardRepository.deleteById(vacBoardId);
    }

//    ip로 조회수 체크
    @Transactional
    public Ip IpChecker(Long id) {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String visitorIp = req.getHeader("X-FORWARDED-FOR");
        if (visitorIp == null)
            visitorIp = req.getRemoteAddr();
        VacBoard vacBoard = vacBoardRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시물 오류")
        );
        Ip ip = new Ip(visitorIp, vacBoard);

//        List<Ip> IpList = ipRepository.findAll();
        boolean isExist = ipRepository.existsByVacBoardAndIp(vacBoard, visitorIp);
        if (!isExist) {
            ipRepository.save(ip);
            vacBoard.updateHits(+1);
        }else {
            vacBoard.updateHits(+0);
        }
        return ip;
    }


    public Page<VacBoard> readVacBoard(int page, int size,  String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return vacBoardRepository.findAll(pageable);
    }


}