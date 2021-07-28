package com.vaccinelife.vaccinelifeapi.dto;


import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long id;
    private Long vBoardId;
    private String nickname;
    private String comment;
}
