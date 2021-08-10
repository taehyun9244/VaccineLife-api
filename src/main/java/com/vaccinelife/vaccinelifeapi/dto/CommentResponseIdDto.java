package com.vaccinelife.vaccinelifeapi.dto;


import com.vaccinelife.vaccinelifeapi.model.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class CommentResponseIdDto {
    private Long id;

    public CommentResponseIdDto(Long id) {
        this.id = id;
    }

    public static CommentResponseIdDto of(Comment comment){
        return CommentResponseIdDto.builder()
                .id(comment.getId()).build();
    }
}
