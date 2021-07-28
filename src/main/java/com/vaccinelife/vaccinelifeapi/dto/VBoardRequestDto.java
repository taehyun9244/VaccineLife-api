package com.vaccinelife.vaccinelifeapi.dto;

public class VBoardRequestDto {
    private Long id;
    private String title;
    private String contents;
    private String nickname;
    private int hits;
    private int commentCount=0;
    private Long userId;
    private Long surveyId;
    private String modifiedAt;
}
