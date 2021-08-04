package com.vaccinelife.vaccinelifeapi.service;

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


    @Transactional
    public VacBoardRequestDto getDetailVacBoard(Long vacBoardId){
        VacBoard vacBoard = vacBoardRepository.findById(vacBoardId).orElseThrow(
                ()-> new IllegalArgumentException("userError")
        );

        return VacBoardRequestDto.of(vacBoard);
    }
    @Transactional
    public List<VacBoardSimRequestDto> getSimpleVacBoard(){
        List<VacBoard> vacBoards = vacBoardRepository.findAllByOrderByCreatedAtDesc();
        return VacBoardSimRequestDto.list(vacBoards);
    }

    @Transactional
    public List<VacBoardSimRequestDto> getTopList(){
        List<VacBoard> vacBoards = vacBoardRepository.findTop3ByOrderByLikeCountDescCreatedAtDesc();
        return VacBoardSimRequestDto.list(vacBoards);
    }



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

    @Transactional
    public Long update(Long vacBoardId, VacBoardRequestDto requestDto) {
        VacBoard vacBoard = vacBoardRepository.findById(vacBoardId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        vacBoard.update(requestDto);
        return vacBoardId;
    }

    @Transactional
    public void deleteVacBoard(Long vacBoardId){
        VacBoard vacBoard = vacBoardRepository.findById(vacBoardId).orElseThrow(
                ()-> new IllegalArgumentException("해당 아이디값을 찾을 수 없습니다.")
        );
        vacBoardRepository.deleteById(vacBoardId);
    }

    @Transactional
    public Ip IpChecker() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String visitorIp = req.getHeader("X-FORWARDED-FOR");
        if (visitorIp == null)
            visitorIp = req.getRemoteAddr();
        Ip ip = new Ip(visitorIp);
        List<Ip> IpList = ipRepository.findAll();
        if (IpList.contains(ip)) {
            ipRepository.save(ip);
            return ip;
        }else{
            return ip;
        }
    }


//    public Page<VacBoard> readVacBoard(int page, int size, String type, String age, String sortBy, boolean isAsc) {
//        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
//        Sort sort = Sort.by(direction, sortBy);
//        Pageable pageable = PageRequest.of(page, size, sort);
//
//        return vacBoardRepository.findAll(pageable);
//    }

    @Transactional
    public Page<VacBoard> getSimpleVacBoard(Pageable pageable) {

        return vacBoardRepository.findAll(pageable);
    }
}