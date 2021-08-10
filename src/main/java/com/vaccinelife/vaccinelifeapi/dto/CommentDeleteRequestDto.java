package com.vaccinelife.vaccinelifeapi.dto;

import com.vaccinelife.vaccinelifeapi.model.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class CommentDeleteRequestDto {
    private  Long id;
    private Long vacBoardId;
    private Long userId;

    @Builder
    public CommentDeleteRequestDto(Long id, Long vacBoardId, Long userId) {
        this.id = id;
        this.vacBoardId = vacBoardId;
        this.userId = userId;
    }

    public static CommentDeleteRequestDto of(Comment comment){
        return CommentDeleteRequestDto.builder()
                .id(comment.getId())
                .vacBoardId(comment.getVacBoard().getId())
                .userId(comment.getUser().getId())
                .build();

    }
}
