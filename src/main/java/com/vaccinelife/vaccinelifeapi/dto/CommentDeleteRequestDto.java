package com.vaccinelife.vaccinelifeapi.dto;

import com.vaccinelife.vaccinelifeapi.model.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class CommentDeleteRequestDto {
    private Long vacBoardId;
    private Long userId;

    @Builder
    public CommentDeleteRequestDto( Long vacBoardId, Long userId) {
        this.vacBoardId = vacBoardId;
        this.userId = userId;
    }

    public static CommentDeleteRequestDto of(Comment comment){
        return CommentDeleteRequestDto.builder()
                .vacBoardId(comment.getVacBoard().getId())
                .userId(comment.getUser().getId())
                .build();

    }
}
