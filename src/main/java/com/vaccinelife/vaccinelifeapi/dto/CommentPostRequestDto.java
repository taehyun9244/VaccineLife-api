package com.vaccinelife.vaccinelifeapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentPostRequestDto {
    private Long boardId;
    private Long userId;
    private String comment;
}
