package com.vaccinelife.vaccinelifeapi.dto;

import com.vaccinelife.vaccinelifeapi.model.VBoard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class VBoardRequestDto {
    private String title;
    private String contents;
    private String nickname;
    private int hits;
    private int commentCount=0;
    private Long userId;
    private Long surveyId;
    private String modifiedAt;
    private int likeCount;


//    public static VBoardRequestDto of (VBoard vBoard){
//        return VBoardRequestDto.builder()
//                .title(vBoard.getTitle())
//                .contents(vBoard.getContents())
//                .nickname(vBoard.getUser().getNickname())
//                .hits(vBoard.getHits())
//                .commentCount(vBoard.getCommentCount())
//                .likeCount(vBoard.getLikeCount())
//                .surveyId(vBoard.getSurvey().getId())
//                .modifiedAt(vBoard.getModifiedAt()).build();
//    }
}
