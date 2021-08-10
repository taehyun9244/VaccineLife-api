package com.vaccinelife.vaccinelifeapi.dto;

import com.vaccinelife.vaccinelifeapi.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentPostRequestDto {
    private Long id;
    private Long vacBoardId;
    private Long userId;
    private String comment;

    @Builder
    public CommentPostRequestDto(Long id) {
        this.id=id;
    }



    public static CommentPostRequestDto of(Comment comment) {
        return CommentPostRequestDto.builder()
                .id(comment.getId())
                .build();
    }


}
