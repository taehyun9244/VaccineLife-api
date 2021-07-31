package com.vaccinelife.vaccinelifeapi.service;

import com.vaccinelife.vaccinelifeapi.dto.CommentPostRequestDto;
import com.vaccinelife.vaccinelifeapi.dto.CommentRequestDto;
import com.vaccinelife.vaccinelifeapi.model.Comment;
import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.model.VacBoard;
import com.vaccinelife.vaccinelifeapi.repository.CommentRepository;
import com.vaccinelife.vaccinelifeapi.repository.UserRepository;
import com.vaccinelife.vaccinelifeapi.repository.VacBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        Comment comment = Comment.builder()
                .user(user)
                .vacBoard(vacBoard)
                .comment(requestDto.getComment()).build();
        commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentRequestDto> getComment(Long vacBoardId) {
        List<Comment> comments = commentRepository.findAllById(vacBoardId);
        return CommentRequestDto.list(comments);
    }

    @Transactional
    public void deleteComment(Long id, CommentRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다.")
        );
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다.")
        );
        if(!comment.getUser().equals(user)){
            throw new IllegalArgumentException("자신이 쓴 댓글만 삭제 할 수 있습니다.");
        }
        commentRepository.delete(comment);


    }

}

