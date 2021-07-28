package com.vaccinelife.vaccinelifeapi.dto;


import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long vBoardId;
    private String username;
    private String comment;
}
