package com.vaccinelife.vaccinelifeapi.dto;


import com.vaccinelife.vaccinelifeapi.model.Comment;

import com.vaccinelife.vaccinelifeapi.model.VBoard;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
@Builder
public class CommentRequestDto {
    private Long boardId;
    private Long userId;
    private String comment;

    public CommentRequestDto(Long boardId, Long userId,String comment){
        this.boardId = boardId;
        this.userId = userId;
        this.comment = comment;
    }

    public static CommentRequestDto of(Comment comment){
        return CommentRequestDto.builder()
                .boardId(comment.getVBoard().getId())
                .userId(comment.getUser().getId())
                .comment(comment.getComment()).build();
    }
    public static List<CommentRequestDto> list(List<Comment> comments){
        ArrayList<CommentRequestDto> commentRequestDtos = new ArrayList<>();
        for(Comment comment : comments){
            commentRequestDtos.add(of(comment));
        }
        return commentRequestDtos;

    }

}
