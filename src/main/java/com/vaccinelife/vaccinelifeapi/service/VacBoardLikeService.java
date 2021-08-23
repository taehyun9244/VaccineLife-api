package com.vaccinelife.vaccinelifeapi.service;

import com.vaccinelife.vaccinelifeapi.dto.VacBoardLikeMypageDto;
import com.vaccinelife.vaccinelifeapi.dto.VacBoardLikeRequestDto;
import com.vaccinelife.vaccinelifeapi.dto.ResponseDto;
import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.model.VacBoardLike;
import com.vaccinelife.vaccinelifeapi.model.VacBoard;
import com.vaccinelife.vaccinelifeapi.repository.UserRepository;
import com.vaccinelife.vaccinelifeapi.repository.VacBoardLikeRepository;
import com.vaccinelife.vaccinelifeapi.repository.VacBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class VacBoardLikeService {

    private final VacBoardLikeRepository vacBoardLikeRepository;
    private final VacBoardRepository vacBoardRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseDto Like(VacBoardLikeRequestDto vacBoardLikeRequestDto) {
        VacBoard vacBoard = vacBoardRepository.findById(vacBoardLikeRequestDto.getVacBoardId()).orElseThrow(
                () -> new NullPointerException("해당 게시물이 존재하지 않습니다.")
        );
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();


        User user = userRepository.findById(vacBoardLikeRequestDto.getUserId()).orElseThrow(
                () -> new NullPointerException("해당 게시물이 존재하지 않습니다.")
        );

        boolean isExist = vacBoardLikeRepository.existsByVacBoardAndUser(vacBoard, user);

        if (isExist) {
            vacBoardLikeRepository.deleteByVacBoardAndUser(vacBoard, user);
            vacBoard.updateLikeNum(-1);
            return new ResponseDto(false, "Basic 게시글 좋아요 취소", 200);
        } else {
            VacBoardLike vacBoardLike = new VacBoardLike(vacBoard, user);
            vacBoardLikeRepository.save(vacBoardLike);
            vacBoard.updateLikeNum(+1);
            return new ResponseDto(true, "Basic 게시글 좋아요 추가", 200);
        }
    }

    public List<VacBoardLikeRequestDto> getLike(Long id) {
        List<VacBoardLike> vacBoardLike = vacBoardLikeRepository.findAllByUserId(id);

        return VacBoardLikeRequestDto.list(vacBoardLike);
    }
    //mypage 내가 좋아한 게시물 내려주기

    public List<VacBoardLikeMypageDto> getLikeMypage(Long id) {
        List<VacBoardLike> vacBoardLike = vacBoardLikeRepository.findAllByUserId(id);

        return VacBoardLikeMypageDto.list(vacBoardLike);
    }
}