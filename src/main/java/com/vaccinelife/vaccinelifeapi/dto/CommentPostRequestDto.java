package com.vaccinelife.vaccinelifeapi.dto;

import com.vaccinelife.vaccinelifeapi.model.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentPostRequestDto {
    private Long vacBoardId;
    private Long userId;
    private String comment;

//    @Builder
//    public CommentPostRequestDto(Long id) {
//        this.id = id;
//    }
//
//    public static CommentRequestDto of(Comment comment){
//        return CommentRequestDto.builder()
//                .id(comment.getId())
//                .vacBoardId(comment.getVacBoard().getId())
//                .userId(comment.getUser().getId())
//                .comment(comment.getComment())
//                .nickname(comment.getUser().getNickname())
//                .createdAt(comment.getCreatedAt())
//                .modifiedAt(comment.getModifiedAt())
//                .build();
//
//    }


}
