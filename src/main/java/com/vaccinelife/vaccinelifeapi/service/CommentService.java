package com.vaccinelife.vaccinelifeapi.service;

import com.vaccinelife.vaccinelifeapi.dto.CommentDeleteRequestDto;
import com.vaccinelife.vaccinelifeapi.dto.CommentPostRequestDto;
import com.vaccinelife.vaccinelifeapi.dto.CommentRequestDto;
import com.vaccinelife.vaccinelifeapi.model.*;
import com.vaccinelife.vaccinelifeapi.repository.CommentRepository;
import com.vaccinelife.vaccinelifeapi.repository.UserRepository;
import com.vaccinelife.vaccinelifeapi.repository.VacBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final VacBoardRepository vacBoardRepository;
    private final UserRepository userRepository;


    @Transactional
    public void createComment(CommentPostRequestDto requestDto) {
        VacBoard vacBoard = vacBoardRepository.findById(requestDto.getVacBoardId()).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다.")
        );
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 안습니다.")
        );

        List<Comment> commentCount = commentRepository.findByVacBoardId(requestDto.getVacBoardId());
        int commentSize = commentCount.size();
        vacBoard.setCommentCount(commentSize+1);

        Comment comment = Comment.builder()
                .user(user)
                .vacBoard(vacBoard)
                .comment(requestDto.getComment()).build();
        commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentRequestDto> getComment(Long vacBoardId) {
        List<Comment> comment = commentRepository.findByVacBoardId(vacBoardId);
        return CommentRequestDto.list(comment);
    }

    @Transactional
    public void deleteComment( Long vacBoardId,Long id) {

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다.")
        );
        VacBoard vacBoard = vacBoardRepository.findById(vacBoardId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물을 찾을 수 없습니다.")
        );

        List<Comment> commentCount = commentRepository.findByVacBoardId(vacBoardId);
        int commentSize = commentCount.size();
        vacBoard.setCommentCount(commentSize-1);

        commentRepository.delete(comment);


    }

}

