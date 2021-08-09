package com.vaccinelife.vaccinelifeapi.service;

import com.vaccinelife.vaccinelifeapi.dto.*;
import com.vaccinelife.vaccinelifeapi.model.Ip;
import com.vaccinelife.vaccinelifeapi.model.QuarBoard;
import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.repository.IpRepository;
import com.vaccinelife.vaccinelifeapi.repository.QuarBoardRepository;
import com.vaccinelife.vaccinelifeapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@Service
public class QuarBoardService {

    private final QuarBoardRepository quarBoardRepository;
    private final UserRepository userRepository;
    private final IpRepository ipRepository;


//    상세 조회
    @Transactional
    public QuarBoardRequestDto getDetailQuarBoard(Long quarBoardId) {
        QuarBoard quarBoard = quarBoardRepository.findById(quarBoardId).orElseThrow(
                () -> new IllegalArgumentException("userError")
        );

        return QuarBoardRequestDto.of(quarBoard);
    }

//    전체 조회
    @Transactional
    public List<QuarBoardSimRequestDto> getSimpleQuarBoard(){
        List<QuarBoard> quarBoards = quarBoardRepository.findAllByOrderByCreatedAtDesc();
        return QuarBoardSimRequestDto.list(quarBoards);
    }

//    탑 3
    @Transactional
    public List<QuarBoardTopRequestDto> getTopList(){
        LocalDateTime week = LocalDateTime.of(LocalDate.now().minusDays(7), LocalTime.of(0,0,0));
        LocalDateTime now = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));
        List<QuarBoard> quarBoards = quarBoardRepository.findTop3ByCreatedAtBetweenOrderByLikeCountDescCreatedAtDesc(week, now);
        return QuarBoardTopRequestDto.list(quarBoards);
    }

//    게시물 작성
    @Transactional
    public void createQuarBoard(QuarBoardPostRequestDto requestDto){
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다.")
        );
        QuarBoard quarBoard = QuarBoard.builder()
                .user(user)
                .title(requestDto.getTitle())
                .contents(requestDto.getContents()).build();
        quarBoardRepository.save(quarBoard);
    }

//     게시물 수정
    @Transactional
    public Long update(Long quarBoardId, QuarBoardRequestDto requestDto) {
        QuarBoard quarBoard = quarBoardRepository.findById(quarBoardId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        quarBoard.update(requestDto);
        return quarBoardId;
    }

//    게시물 삭제
    @Transactional
    public void deleteQuarBoard(Long quarBoardId){
        QuarBoard quarBoard = quarBoardRepository.findById(quarBoardId).orElseThrow(
                ()-> new IllegalArgumentException("해당 아이디값을 찾을 수 없습니다.")
        );
        quarBoardRepository.deleteById(quarBoardId);
    }

//    조회수 체크
    @Transactional
    public Ip QuarIpChecker(Long id) {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String visitorIp = req.getHeader("X-FORWARDED-FOR");
        if (visitorIp == null)
            visitorIp = req.getRemoteAddr();
        QuarBoard quarBoard = quarBoardRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시물 오류")
        );
        Ip ip = new Ip(visitorIp, quarBoard);

//        List<Ip> IpList = ipRepository.findAll();
        boolean isExist = ipRepository.existsByQuarBoardAndIp(quarBoard, visitorIp);
        if (!isExist) {
            ipRepository.save(ip);
            quarBoard.updateHits(+1);
        }else {
            quarBoard.updateHits(+0);
        }
        return ip;
    }

//    페이지 처리
    public Page<QuarBoard> readQuarBoard(int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return quarBoardRepository.findAllByOrderByCreatedAtDesc(pageable);
    }
}