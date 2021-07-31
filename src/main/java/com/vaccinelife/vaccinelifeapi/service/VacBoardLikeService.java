package com.vaccinelife.vaccinelifeapi.service;

import com.vaccinelife.vaccinelifeapi.controller.security.UserDetailsImpl;
import com.vaccinelife.vaccinelifeapi.dto.ResponseDto;
import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.model.VacBoardLike;
import com.vaccinelife.vaccinelifeapi.model.VacBoard;
import com.vaccinelife.vaccinelifeapi.model.VacBoardLike;
import com.vaccinelife.vaccinelifeapi.repository.VacBoardLikeRepository;
import com.vaccinelife.vaccinelifeapi.repository.VacBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class VacBoardLikeService {

    private final VacBoardLikeRepository vacBoardLikeRepository;
    private final VacBoardRepository vacBoardRepository;

    @Transactional
    public ResponseDto Like(Long vacBoardId) {
        VacBoard vacBoard = vacBoardRepository.findById(vacBoardId).orElseThrow(
                () -> new NullPointerException("해당 아티클이 존재하지 않습니다.")
        );
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();

        boolean isExist = vacBoardLikeRepository.existsByVacBoardAndUser(vacBoard, user);

        if (isExist) {
            vacBoardLikeRepository.deleteByVacBoardAndUser(vacBoard, user);
            vacBoard.deleteLikeNum(-1);
            return new ResponseDto(true, "Basic 게시글 좋아요 취소", 200);
        } else {
            VacBoardLike vacBoardLike = new VacBoardLike(vacBoard, user);
            vacBoardLikeRepository.save(vacBoardLike);
            vacBoard.updateLikeNum(+1);
            return new ResponseDto(true, "Basic 게시글 좋아요 추가", 200);
        }
    }
}