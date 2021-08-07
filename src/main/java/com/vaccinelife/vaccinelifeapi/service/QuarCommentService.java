package com.vaccinelife.vaccinelifeapi.service;

import com.vaccinelife.vaccinelifeapi.dto.*;
import com.vaccinelife.vaccinelifeapi.model.*;
import com.vaccinelife.vaccinelifeapi.repository.QuarBoardRepository;
import com.vaccinelife.vaccinelifeapi.repository.QuarCommentRepository;
import com.vaccinelife.vaccinelifeapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuarCommentService {

    private final UserRepository userRepository;
    private final QuarBoardRepository quarBoardRepository;
    private final QuarCommentRepository quarCommentRepository;

//    댓글 작성
    @Transactional
    public void createQuarComment(QuarCommentPostRequestDto requestDto) {
        QuarBoard quarBoard = quarBoardRepository.findById(requestDto.getQuarBoardId()).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다.")
        );
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 안습니다.")
        );

        List<QuarComment> quarComments = quarCommentRepository.findByQuarBoardId(requestDto.getQuarBoardId());
        int commentSize = quarComments.size();
        quarBoard.setCommentCount(commentSize+1);

        QuarComment quarComment = QuarComment.builder()
                .user(user)
                .quarBoard(quarBoard)
                .quarcomment(requestDto.getQuarcomment()).build();
        quarCommentRepository.save(quarComment);
    }

//    댓글 삭제
    @Transactional
    public void deleteComment(Long id, QuarCommentRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다.")
        );
        QuarComment quarComment = quarCommentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다.")
        );
        QuarBoard quarBoard = quarBoardRepository.findById(requestDto.getQuarBoardId()).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다.")
        );

        List<QuarComment> quarComments = quarCommentRepository.findByQuarBoardId(requestDto.getQuarBoardId());
        int commentSize = quarComments.size();
        quarBoard.setCommentCount(commentSize-1);

        if(!quarComment.getUser().equals(user)){
            throw new IllegalArgumentException("자신이 쓴 댓글만 삭제 할 수 있습니다.");
        }
        quarCommentRepository.delete(quarComment);


    }

//    댓글 조회
    @Transactional(readOnly = true)
    public List<QuarCommentRequestDto> getQuarComment(Long quarBoardId) {
        List<QuarComment> quarComments = quarCommentRepository.findByQuarBoardId(quarBoardId);
        return QuarCommentRequestDto.list(quarComments);
    }
}
