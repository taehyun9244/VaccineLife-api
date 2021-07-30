package com.vaccinelife.vaccinelifeapi.dto;

import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.model.VBoard;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VBoardRequestDto {
    private String title;
    private String contents;
    private int hits;
    private int commentCount;
    private int likeCount;
    private User user;

    public static VBoardRequestDto of(VBoard vBoard){
        return VBoardRequestDto.builder()
                .title(vBoard.getTitle())
                .contents(vBoard.getContents())
                .hits(vBoard.getHits())
                .commentCount(vBoard.getCommentCount())
                .likeCount(vBoard.getLikeCount())
                .user(vBoard.getUser())
                .build();
    }
}