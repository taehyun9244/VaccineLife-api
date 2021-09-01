package com.vaccinelife.vaccinelifeapi.service;

import com.vaccinelife.vaccinelifeapi.dto.*;
import com.vaccinelife.vaccinelifeapi.model.Ip;
import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.model.VacBoard;
import com.vaccinelife.vaccinelifeapi.repository.IpRepository;
import com.vaccinelife.vaccinelifeapi.repository.UserRepository;
import com.vaccinelife.vaccinelifeapi.repository.VacBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class VacBoardService {

    private final VacBoardRepository vacBoardRepository;
    private final UserRepository userRepository;
    private final IpRepository ipRepository;

    //이전글 다음글
    @Transactional
    public VacPrevNextDto getVacNextPrevId(Long vaBoardId){
        VacBoard prevId = vacBoardRepository.findTopByIdLessThanOrderByCreatedAtDesc(vaBoardId);
        VacBoard nextId = vacBoardRepository.findFirstByIdGreaterThan(vaBoardId);
        return VacPrevNextDto.builder()
                .prevId(prevId)
                .nextId(nextId)
                .build();
    }


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
    public List<VacBoardTopRequestDto> getTopList(){
        LocalDateTime week = LocalDateTime.of(LocalDate.now().minusDays(7), LocalTime.of(0,0,0));
        LocalDateTime now = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));
        List<VacBoard> vacBoards = vacBoardRepository.findTop3ByCreatedAtBetweenOrderByLikeCountDescCreatedAtDesc(week, now);
        return VacBoardTopRequestDto.list(vacBoards);
    }

// 게시물 작성
    @Transactional
    public void createVacBoard(VacBoardPostRequestDto requestDto){
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다.")
        );
        vacBoardRepository.save(requestDto.toEntity(user));
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
        String visitorIp = req.getHeader("X-Forwarded-For");



        if (visitorIp == null) {
        visitorIp = req.getHeader("Proxy-Client-IP");
        }

        if (visitorIp == null) {
        visitorIp = req.getHeader("WL-Proxy-Client-IP");
        }

        if (visitorIp == null) {
        visitorIp = req.getHeader("HTTP_CLIENT_IP");
        }
        if (visitorIp == null) {
        visitorIp = req.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (visitorIp == null) {
        visitorIp = req.getRemoteAddr();
        }

        VacBoard vacBoard = vacBoardRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시물 오류")
        );
        Ip ip = new Ip(visitorIp, vacBoard);


        boolean isExist = ipRepository.existsByVacBoardAndIp(vacBoard, visitorIp);
        if (!isExist) {
            ipRepository.save(ip);
            vacBoard.updateHits(+1);
        }else {
            vacBoard.updateHits(+1);  //현재 https 보안상 이슈로 유저의 ip를 받을 수 없어 ip존재여부 관계 없이 카운트 수 증가하게 처리
        }
        return ip;
    }
//mypage vacboard
    @Transactional
    public List<VacBoardSimRequestDto> getMypageVacBoard(Long userId){
        List<VacBoard> vacBoards = vacBoardRepository.findAllByUserIdOrderByCreatedAtDesc(userId);
        return VacBoardSimRequestDto.list(vacBoards);
    }

    //게시판 전체 조회 페이지네이션
    public Page<VacBoardSimRequestDto> readVacBoard(int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return vacBoardRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    //백신 종류별 필터링+ 페이지네이션 동시 api
    public Page<VacBoardSimRequestDto> readVacBoardType(int page, int size, String sortBy, boolean isAsc, String type) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return vacBoardRepository.findAllByUserTypeOrderByCreatedAtDesc(pageable,type);
    }


}