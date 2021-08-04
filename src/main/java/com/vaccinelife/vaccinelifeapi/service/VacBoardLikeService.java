package com.vaccinelife.vaccinelifeapi.service;

import com.vaccinelife.vaccinelifeapi.dto.LikeRequestDto;
import com.vaccinelife.vaccinelifeapi.dto.ResponseDto;
import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.model.VacBoardLike;
import com.vaccinelife.vaccinelifeapi.model.VacBoard;
import com.vaccinelife.vaccinelifeapi.repository.UserRepository;
import com.vaccinelife.vaccinelifeapi.repository.VacBoardLikeRepository;
import com.vaccinelife.vaccinelifeapi.repository.VacBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class VacBoardLikeService {

    private final VacBoardLikeRepository vacBoardLikeRepository;
    private final VacBoardRepository vacBoardRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseDto Like(LikeRequestDto likeRequestDto) {
        VacBoard vacBoard = vacBoardRepository.findById(likeRequestDto.getVacBoardId()).orElseThrow(
                () -> new NullPointerException("해당 게시물이 존재하지 않습니다.")
        );
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();


        User user = userRepository.findById(likeRequestDto.getUserId()).orElseThrow(
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

}