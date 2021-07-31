package com.vaccinelife.vaccinelifeapi.dto;


import com.vaccinelife.vaccinelifeapi.model.Comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Builder
public class CommentRequestDto {
    private Long vacBoardId;
    private Long userId;
    private String comment;

    public CommentRequestDto(Long vacBoardId, Long userId,String comment){
        this.vacBoardId = vacBoardId;
        this.userId = userId;
        this.comment = comment;
    }

    public static CommentRequestDto of(Comment comment){
        return CommentRequestDto.builder()
                .vacBoardId(comment.getVacBoard().getId())
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
