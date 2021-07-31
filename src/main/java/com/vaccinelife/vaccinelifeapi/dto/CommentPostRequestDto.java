package com.vaccinelife.vaccinelifeapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentPostRequestDto {
    private Long vacBoardId;
    private Long userId;
    private String comment;
}
